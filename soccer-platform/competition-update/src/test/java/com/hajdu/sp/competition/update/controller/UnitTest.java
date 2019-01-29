package com.hajdu.sp.competition.update.controller;

import org.junit.Test;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.TemplateVariable;
import org.springframework.hateoas.TemplateVariables;
import org.springframework.hateoas.UriTemplate;

public class UnitTest {

    @Test
    public void test() {
        Link link = new Link(new UriTemplate("api/base", new TemplateVariables(new TemplateVariable("projection", TemplateVariable.VariableType.REQUEST_PARAM))), "rel");
        System.out.println(link.getHref());
    }

}
