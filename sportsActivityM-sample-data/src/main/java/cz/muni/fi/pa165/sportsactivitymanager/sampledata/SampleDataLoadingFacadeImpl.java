package cz.muni.fi.pa165.sportsactivitymanager.sampledata;

import cz.muni.fi.pa165.sportsactivitymanager.Entity.Activity;
import cz.muni.fi.pa165.sportsactivitymanager.Entity.ActivityRecord;
import cz.muni.fi.pa165.sportsactivitymanager.Entity.Calories;
import cz.muni.fi.pa165.sportsactivitymanager.Entity.User;
import cz.muni.fi.pa165.sportsactivitymanager.Enums.Sex;
import cz.muni.fi.pa165.sportsactivitymanager.service.ActivityRecordService;
import cz.muni.fi.pa165.sportsactivitymanager.service.ActivityService;
import cz.muni.fi.pa165.sportsactivitymanager.service.CaloriesService;
import cz.muni.fi.pa165.sportsactivitymanager.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Calendar;

/**
 * Loads some sample data to populate the eshop database.
 *
 * @author Michal Stefanik 422237
 */
@Component
@Transactional
public class SampleDataLoadingFacadeImpl implements SampleDataLoadingFacade {

    final static Logger log = LoggerFactory.getLogger(SampleDataLoadingFacadeImpl.class);

    @Autowired
    private ActivityRecordService activityRecordService;
    @Autowired
    private ActivityService activityService;
    @Autowired
    private CaloriesService caloriesService;
    @Autowired
    private UserService userService;

    @Override
    @SuppressWarnings("unused")
    public void loadData() throws IOException {
        Activity a1;
        Activity a2;
        Activity a3;
        Activity a4;

        User user1 = new User();
        User user2 = new User();

        //activity1
        a1 = new Activity();
        a1.setMeasureDistance(false);
        a1.setName("Volley");

        Calories c1 = new Calories();
        c1.setIndex(10.8);
        a1.setCalories(c1);

        //activity3
        a3 = new Activity();
        a3.setMeasureDistance(true);
        a3.setName("Volley");

        Calories c3 = new Calories();
        c3.setIndex(8.10);
        a3.setCalories(c3);

        //activity4
        a4 = new Activity();
        a4.setMeasureDistance(false);
        a4.setName("Basket");

        Calories c4 = new Calories();
        c4.setIndex(8.10);
        a4.setCalories(c4);

        user1.setName("Peter");
        user1.setEmail("peter@test.com");
        user1.setAge(20);
        user1.setSex(Sex.Male);
        user1.setWeight(90.5);
        user1.setHeight(189.0);

        user2.setName("Martina");
        user2.setEmail("martina@test.com");
        user2.setAge(40);
        user2.setSex(Sex.Female);
        user2.setWeight(70.5);
        user2.setHeight(150.9);

        Calendar date1 = Calendar.getInstance();
        Calendar date2 = Calendar.getInstance();
        date1.set(2015, 5, 23);
        date2.set(2015,8,4);

        ActivityRecord activityRecord1 = new ActivityRecord();
        ActivityRecord activityRecord2 = new ActivityRecord();
        ActivityRecord activityRecord3 = new ActivityRecord();

        activityRecord1.setActivity(a1);
        activityRecord1.setDate(date1);
        activityRecord1.setDuration(120.0);
        activityRecord1.setUser(user1);

        activityRecord2.setActivity(a3);
        activityRecord2.setDate(date2);
        activityRecord2.setDuration(35.5);
        activityRecord2.setUser(user2);
        activityRecord2.setDistance(55.3);

        activityRecord3.setActivity(a4);
        activityRecord3.setDate(date1);
        activityRecord3.setDuration(67.7);
        activityRecord3.setUser(user2);

        activityRecordService.createRecord(activityRecord1);
        activityRecordService.createRecord(activityRecord2);
        activityRecordService.createRecord(activityRecord3);
    }

}
