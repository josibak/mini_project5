package miniproject.infra;

import miniproject.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class MemberHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Member>> {

    @Override
    public EntityModel<Member> process(EntityModel<Member> model) {
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/openbookpoint")
                .withRel("openbookpoint")
        );
        model.add(
            Link
                .of(
                    model.getRequiredLink("self").getHref() +
                    "/subscribtionrequest"
                )
                .withRel("subscribtionrequest")
        );
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/registermember")
                .withRel("registermember")
        );
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/authkt")
                .withRel("authkt")
        );
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/bookopen")
                .withRel("bookopen")
        );

        return model;
    }
}
