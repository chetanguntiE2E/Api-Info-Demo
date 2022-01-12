package com.example.application.views.list;

import com.example.application.data.entity.Apiinfo;
import com.example.application.data.entity.Apitype;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;

import java.util.List;

public class ApiForm extends FormLayout {
    Binder<Apiinfo> binder = new BeanValidationBinder<>(Apiinfo.class);
    TextField apiName = new TextField("Api name");
    ComboBox<Apitype> apitype = new ComboBox<>("Api type");
    TextField purpose = new TextField("Purpose");
    TextField request = new TextField("Request");
    TextField response = new TextField("Response");
    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");
    private Apiinfo apiinfo;

    public ApiForm(List<Apitype> apitypes){
        addClassName("api-form");
        binder.bindInstanceFields(this);
        apitype.setItems(apitypes);
        apitype.setItemLabelGenerator(Apitype::getApitypename);
        add (apitype,apiName,purpose,request,response,createButtonsLayout());
    }

    public void setApiinfo(Apiinfo apiinfo){
        this.apiinfo=apiinfo;
        binder.readBean(apiinfo);
    }
    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new DeleteEvent(this, apiinfo)));
        close.addClickListener(event -> fireEvent(new CloseEvent(this)));
        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);
        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave(){
        try{
            binder.writeBean(apiinfo);
            fireEvent(new SaveEvent(this,apiinfo));
        } catch (ValidationException e){
            e.printStackTrace();
        }
    }


    public static abstract class ApiFormEvent extends ComponentEvent<ApiForm> {
        private Apiinfo apiinfo;
        protected ApiFormEvent(ApiForm source, Apiinfo apiinfo) {
            super(source, false);
            this.apiinfo = apiinfo;
        }

        public Apiinfo getApiinfo() {
            return apiinfo;
        }
    }

    public static class SaveEvent extends ApiFormEvent {
        SaveEvent(ApiForm source, Apiinfo apiinfo) {
            super(source, apiinfo);
        }
    }

    public static class DeleteEvent extends ApiFormEvent {
        DeleteEvent(ApiForm source, Apiinfo apiinfo) {
            super(source, apiinfo);
        }

    }

    public static class CloseEvent extends ApiFormEvent {
        CloseEvent(ApiForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}

