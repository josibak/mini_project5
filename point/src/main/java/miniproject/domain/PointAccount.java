package miniproject.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import lombok.Data;
import miniproject.PointApplication;
import miniproject.domain.BasicPointGranted;
import miniproject.domain.KtPointGranted;
import miniproject.domain.PointDeducted;

@Entity
@Table(name = "PointAccount_table")
@Data
//<<< DDD / Aggregate Root
public class PointAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pointAccountId;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    private Integer balance;

    public static PointAccountRepository repository() {
        PointAccountRepository pointAccountRepository = PointApplication.applicationContext.getBean(
            PointAccountRepository.class
        );
        return pointAccountRepository;
    }

    //<<< Clean Arch / Port Method
    public static void bookOpenedByPoint(PointBookOpened pointBookOpened) {
        //implement business logic here:

        /** Example 1:  new item 
        PointAccount pointAccount = new PointAccount();
        repository().save(pointAccount);

        PointDeducted pointDeducted = new PointDeducted(pointAccount);
        pointDeducted.publishAfterCommit();
        */

        /** Example 2:  finding and process
        

        repository().findById(pointBookOpened.get???()).ifPresent(pointAccount->{
            
            pointAccount // do something
            repository().save(pointAccount);

            PointDeducted pointDeducted = new PointDeducted(pointAccount);
            pointDeducted.publishAfterCommit();

         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void userRegistered(MemberRegistered memberRegistered) {
        //implement business logic here:

        /** Example 1:  new item 
        PointAccount pointAccount = new PointAccount();
        repository().save(pointAccount);

        BasicPointGranted basicPointGranted = new BasicPointGranted(pointAccount);
        basicPointGranted.publishAfterCommit();
        */

        /** Example 2:  finding and process
        

        repository().findById(memberRegistered.get???()).ifPresent(pointAccount->{
            
            pointAccount // do something
            repository().save(pointAccount);

            BasicPointGranted basicPointGranted = new BasicPointGranted(pointAccount);
            basicPointGranted.publishAfterCommit();

         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void ktMemberVerified(KtAuthenticated ktAuthenticated) {
        //implement business logic here:

        /** Example 1:  new item 
        PointAccount pointAccount = new PointAccount();
        repository().save(pointAccount);

        KtPointGranted ktPointGranted = new KtPointGranted(pointAccount);
        ktPointGranted.publishAfterCommit();
        */

        /** Example 2:  finding and process
        

        repository().findById(ktAuthenticated.get???()).ifPresent(pointAccount->{
            
            pointAccount // do something
            repository().save(pointAccount);

            KtPointGranted ktPointGranted = new KtPointGranted(pointAccount);
            ktPointGranted.publishAfterCommit();

         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
