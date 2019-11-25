
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shente.cams.CamsApplication;
import com.shente.cams.controller.CourseController;
import com.shente.cams.controller.CourseResController;
import com.shente.cams.dto.Result;
import com.shente.cams.mapper.ResultMapper;
import com.shente.cams.pojo.Course;
import com.shente.cams.service.ScheduleService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={CamsApplication.class})
class CamsApplicationTests {
    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private ResultMapper resultMapper;



    @Test
    public void TestScheduleService() throws IOException {

        String s = scheduleService.coursesArranging(15);
        System.out.println(s);
    }

    @Test
    public void testResultMapper(){
        List<String> list = resultMapper.queryResultDataByCourseId(15, 'S');
        for (String l:list){
            System.out.println(l);
        }
    }

    @Autowired
    private CourseController courseController;

    @Test
    public void testChangeCourseStatus() throws JsonProcessingException {
        final ObjectMapper mapper = new ObjectMapper();
        Course course=new Course();
        course.setCId(15);
        course.setState(0);
        Result result = courseController.changeCourseStatus(course);

        System.out.println(mapper.writeValueAsString(result));
    }



    @Test
    public void deleteResultTest(){
        resultMapper.deleteResult(2,"T");
    }

}
