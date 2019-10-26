
import com.fasterxml.jackson.core.JsonProcessingException;
import com.shente.cams.CamsApplication;
import com.shente.cams.service.ScheduleService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={CamsApplication.class})
class CamsApplicationTests {
    @Autowired
    private ScheduleService scheduleService;


    @Test
    public void TestScheduleService(){

    }

}
