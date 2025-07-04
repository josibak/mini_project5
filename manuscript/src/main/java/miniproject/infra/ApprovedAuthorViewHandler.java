// package miniproject.infra;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.cloud.stream.annotation.StreamListener;
// import org.springframework.messaging.handler.annotation.Payload;
// import org.springframework.stereotype.Service;
// import miniproject.config.kafka.KafkaProcessor;
// import miniproject.domain.*;

// @Service
// public class ApprovedAuthorViewHandler {

//     //<<< DDD / CQRS
//     @Autowired
//     private ApprovedAuthorRepository approvedAuthorRepository;

//     @StreamListener(
//         value = KafkaProcessor.INPUT
//         // condition = "headers['type'] == 'AuthorRegistrationApproved'"
//     )
//     public void whenAuthorRegistrationApproved_then_CREATE_1(
//         @Payload AuthorRegistrationApproved authorRegistrationApproved
//     ) {
//         try {
//             // 이벤트 객체가 유효한지 사전 검증(필드 누락, 잘못된 형식)
//             // 내가 처리할 이벤트 메시지가 맞는지 필터링하는 역할
//             // Payload 안의 eventType 필드로 필터링
//             if (!authorRegistrationApproved.validate()) return;

//             // view 객체 생성
//             ApprovedAuthor approvedAuthor = new ApprovedAuthor();
//             // view 객체에 이벤트의 Value 를 set 함
//             approvedAuthor.setAuthorId(
//                 authorRegistrationApproved.getAuthorId()
//             );
//             // view 레파지토리에 save
//             approvedAuthorRepository.save(approvedAuthor);

//             System.out.println("승인된 작가 저장 완료: " + authorRegistrationApproved.getAuthorId());

//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//     }
//     //>>> DDD / CQRS
// }