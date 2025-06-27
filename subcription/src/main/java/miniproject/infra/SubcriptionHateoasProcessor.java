package miniproject.infra;

import miniproject.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class SubcriptionHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Subcription>> {

    @Override
    public EntityModel<Subcription> process(EntityModel<Subcription> model) {
        return model;
    }
}
