package io.github.yhugorocha.resource;

import io.github.yhugorocha.service.OpportunityService;
import io.github.yhugorocha.utils.XLSXHelper;
import io.quarkus.panache.common.Page;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/opportunity")
public class OpportunityResource {

    @Inject
    OpportunityService opportunityService;

    @GET
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response getOpportunity(){
        return Response.ok(opportunityService.generateXLSXOpportunityReport().toByteArray())
                .header("Content-Disposition", "attachment; filename=opportunity.xlsx")
                .build();
    }
}
