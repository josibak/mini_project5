package miniproject.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "external-service", url = "http://localhost:8086") 
public interface Service {
    @PostMapping("/member")
    MemberInfo member(@RequestBody MemberQuery query);
}
