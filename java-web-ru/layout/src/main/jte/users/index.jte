<!-- BEGIN -->
@import exercise.dto.users.UsersPage
@param UsersPage page

@template.layout.page(
content = @`
    <div class="mx-auto p-4 py-md-5">
        <h1>Список пользователей</h1>
        @if(page.getUsers().isEmpty())
            <p>Пока не добавлено ни одного пользователя</p>
        @else
            @for(var user : page.getUsers())
                <div>
                    <h2><a href="/users/${user.getId()}">${user.getFirstName()} ${user.getLastName()}</a></h2>
                    <p>${user.getEmail()}</p>
                </div>
            @endfor
        @endif
    </div>
`
)
<!-- END -->
