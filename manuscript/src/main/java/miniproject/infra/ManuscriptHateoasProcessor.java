package miniproject.infra;

import miniproject.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class ManuscriptHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Manuscript>> {

    @Override
    public EntityModel<Manuscript> process(EntityModel<Manuscript> model) {
        model.add(
            Link
                .of(
                    model.getRequiredLink("self").getHref() +
                    "/createmanuscript"
                )
                .withRel("createmanuscript")
        );
        model.add(
            Link
                .of(
                    model.getRequiredLink("self").getHref() +
                    "/updatemanuscript"
                )
                .withRel("updatemanuscript")
        );
        model.add(
            Link
                .of(
                    model.getRequiredLink("self").getHref() +
                    "/requestpublication"
                )
                .withRel("requestpublication")
        );
        model.add(
            Link
                .of(
                    model.getRequiredLink("self").getHref() +
                    "/savefinalmanuscript"
                )
                .withRel("savefinalmanuscript")
        );

        return model;
    }
}
