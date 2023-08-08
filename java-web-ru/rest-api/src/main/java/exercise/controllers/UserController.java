package exercise.controllers;

import io.javalin.http.Context;
import io.javalin.apibuilder.CrudHandler;
import io.ebean.DB;
import java.util.List;
import java.util.Map;

import exercise.domain.User;
import exercise.domain.query.QUser;

import io.javalin.validation.BodyValidator;
import io.javalin.validation.JavalinValidation;
import io.javalin.validation.ValidationError;
import io.javalin.validation.Validator;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.lang3.StringUtils;

public class UserController implements CrudHandler {

    public void getAll(Context ctx) {
        // BEGIN
        List<User> users = new QUser()
                .orderBy()
                .id.asc()
                .findList();

        String json = DB.json().toJson(users);
        ctx.json(json);
        // END
    };

    public void getOne(Context ctx, String id) {

        // BEGIN
        User user = new QUser()
                .id.equalTo(Long.parseLong(id))
                .findOne();

        String json = DB.json().toJson(user);
        ctx.json(json);
        // END
    };

    public void create(Context ctx) {

        String body = ctx.body();
        User user = DB.json().toBean(User.class, body);

        BodyValidator<User> userValidator = ctx.bodyValidator(User.class)
            .check(obj -> !obj.getFirstName().isEmpty(), "Имя не должно быть пустым")
            .check(obj -> !obj.getLastName().isEmpty(), "Фамилия не должна быть пустой");

        // Валидируем данные из форм и собираем все ошибки валидации в словарь
        // Если ошибок нет, словарь будет пустой
        Map<String, List<ValidationError<? extends Object>>> errors = JavalinValidation.collectErrors(
                userValidator
        );

        if (!errors.isEmpty()) {
            ctx.status(422);
            return;
        }

        user.save();
        // END
    };

    public void update(Context ctx, String id) {
        // BEGIN
        String body = ctx.body();
        User user = DB.json().toBean(User.class, body);
        user.setId(id);
        user.update();

        // END
    };

    public void delete(Context ctx, String id) {
        // BEGIN
        new QUser()
                .id.equalTo(Long.parseLong(id))
                .delete();

        // END
    };
}
