package com.mycompany;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.validation.validator.EmailAddressValidator;
import org.apache.wicket.validation.validator.PatternValidator;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;

    public HomePage(final PageParameters parameters) {
		add(new Label("version", "Nome: "));
                add(new Label("name", "CPF: "));
                add(new Label("mail", "Email: "));
                TextField mail;

	add(mail = new TextField("username", Model.of("")));
        EmailAddressValidator validator = EmailAddressValidator.getInstance();
	mail.add(validator);
    }
}
