package miniproject.infra;

import miniproject.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class PointAccountHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<PointAccount>> {

    @Override
    public EntityModel<PointAccount> process(EntityModel<PointAccount> model) {
        return model;
    }
}
