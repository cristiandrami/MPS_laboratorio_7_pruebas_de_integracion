package org.mps.authentication;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class prova {
    @DisplayName("[UserRegistration:register] if validation status has to be VALIDATION_OK the method has to call register method of CredentialStore ")
    @Test
    void should_UserRegistration_register_has_statusOK_credentialStore_isCalled(){
        CredentialStore credentialStore= Mockito.mock(CredentialStore.class);
        CredentialValidator credentialValidator= Mockito.mock(CredentialValidator.class);
        Mockito.doReturn(CredentialValidator.ValidationStatus.VALIDATION_OK).when(credentialValidator.validate());
        //Mockito.when(credentialStore.register(Mockito.any(), Mockito.any()));
        Date date = Mockito.mock(Date.class);
        PasswordString password= Mockito.mock(PasswordString.class);



        UserRegistration userRegistration= new UserRegistration();
        userRegistration.register(date,password,credentialStore,credentialValidator);

        Mockito.verify(credentialStore).register(date, password);
    }
}
