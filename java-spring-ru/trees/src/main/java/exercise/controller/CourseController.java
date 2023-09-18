package exercise.controller;

import exercise.model.Course;
import exercise.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseRepository courseRepository;

    @GetMapping(path = "")
    public Iterable<Course> getCorses() {
        return courseRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Course getCourse(@PathVariable long id) {
        return courseRepository.findById(id);
    }

    // BEGIN

    @GetMapping(path = "/{id}/previous")
    public List<Course> getPreviousCourses(@PathVariable long id) {
        String path = courseRepository.findById(id).getPath();
        List<Long> ids = new ArrayList<>();
        List<Course> previousCourses = new ArrayList<>();
        if (path != null && !path.equals("")) {
            ids = Arrays.stream(path.split("\\."))
                    .map(Long::parseLong)
                    .toList();
            for (long parentId : ids) {
                previousCourses.add(courseRepository.findById(parentId));
            }
        }
        return previousCourses;
    }
    // END

}
