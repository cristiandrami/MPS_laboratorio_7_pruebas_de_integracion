package org.mps.authentication;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;

import static org.assertj.core.api.Assertions.*;


public class IntegrationTestIT {


    @DisplayName("[CredentialValidator:validate] if date and password are valid, check if is ")
    @Test
    void should_CredentialValidator_checkIfDateAndPassword_areOk() {
        Date date = Mockito.mock(Date.class);
        CredentialStore credentialStore = Mockito.mock(CredentialStore.class);
        PasswordString password = Mockito.mock(PasswordString.class);
        CredentialValidator credentialValidator = new CredentialValidator(date, password, credentialStore);

        Mockito.when(credentialValidator.validate()).thenReturn(CredentialValidator.ValidationStatus.VALIDATION_OK);
        Assert.assertEquals(credentialValidator.validate(), CredentialValidator.ValidationStatus.VALIDATION_OK);

        //  Mockito.doReturn(CredentialValidator.ValidationStatus.VALIDATION_OK).when(credentialValidator.validate());

        Mockito.verify(credentialValidator).validate();


    }

    @DisplayName("[CredentialValidator:validate] if birthday valid, return birthday invalid")
    @Test
    void should_CredentialValidator_returnBirthdayInvalid_ifBirthdayNotCorrect() {
        Date date = new Date(32, 11, 2002);
        CredentialStore credentialStore = Mockito.mock(CredentialStore.class);
        PasswordString password = Mockito.mock(PasswordString.class);
        CredentialValidator credentialValidator = new CredentialValidator(date, password, credentialStore);

        UserRegistration userRegistration = new UserRegistration();
        userRegistration.register(date, password, credentialStore, credentialValidator);

        Mockito.verify(credentialStore).register(date, password);


    }

    @DisplayName("[CredentialValidator:validate] if birthday valid, return birthday invalid")
    @Test
    void should_CredentialValidator_returnBirthdayInvalid_ifBirthdayNotCorrect() {
        Date date = new Date(22, 11, 2002);
        CredentialStore credentialStore = Mockito.mock(CredentialStore.class);
        PasswordString password = Mockito.mock(PasswordString.class);
        CredentialValidator credentialValidator = new CredentialValidator(date, password, credentialStore);

        Mockito.doReturn(CredentialValidator.ValidationStatus.BIRTHDAY_INVALID).when(credentialValidator.validate());

        Mockito.verify(credentialValidator.validate());


    }


    /***
     *
     *
     * NESTED TESTS
     *
     *
     */


    @Nested
    class NoIntegrationTests {
        @DisplayName("[UserRegistration:register] if validation status has to be VALIDATION_OK the method has to call register method of CredentialStore ")
        @Test
        void should_UserRegistration_registerWithValidationStatusOK_hasToCall_registerMethodOfCredentialStore() {
            CredentialStore credentialStore = Mockito.mock(CredentialStore.class);
            CredentialValidator credentialValidator = Mockito.mock(CredentialValidator.class);
            Mockito.when(credentialValidator.validate()).thenReturn(CredentialValidator.ValidationStatus.VALIDATION_OK);
            //Mockito.when(credentialStore.register(Mockito.any(), Mockito.any()));
            Date date = Mockito.mock(Date.class);
            PasswordString password = Mockito.mock(PasswordString.class);


            UserRegistration userRegistration = new UserRegistration();
            userRegistration.register(date, password, credentialStore, credentialValidator);

            Mockito.verify(credentialStore).register(date, password);
        }
    }

    @Nested
    class CredentialValidatorIntegration {
        @DisplayName("[UserRegistration:register] if validation status is VALIDATION_OK the method has to call register method of CredentialStore ")
        @Test
        void should_UserRegistration_registerWithValidationStatusOK_hasToCall_registerMethodOfCredentialStore() {
            CredentialStore credentialStore = Mockito.mock(CredentialStore.class);


            //Mockito.when(credentialStore.register(Mockito.any(), Mockito.any()));
            Date date = Mockito.mock(Date.class);
            PasswordString password = Mockito.mock(PasswordString.class);

            Mockito.when(date.validate()).thenReturn(true);
            Mockito.when(password.validate()).thenReturn(true);
            Mockito.when(credentialStore.credentialExists(date, password)).thenReturn(false);
            CredentialValidator credentialValidator = new CredentialValidator(date, password, credentialStore);


            UserRegistration userRegistration = new UserRegistration();
            userRegistration.register(date, password, credentialStore, credentialValidator);

            Mockito.verify(credentialStore).register(date, password);
        }

        @DisplayName("[UserRegistration:register] if password is not valid register method of CredentialStore is not called ")
        @Test
        void should_UserRegistration_registerWithPasswordNotValid_hasNotToCall_registerMethodOfCredentialStore() {
            CredentialStore credentialStore = Mockito.mock(CredentialStore.class);


            //Mockito.when(credentialStore.register(Mockito.any(), Mockito.any()));
            Date date = Mockito.mock(Date.class);
            PasswordString password = Mockito.mock(PasswordString.class);

            Mockito.when(date.validate()).thenReturn(true);
            Mockito.when(password.validate()).thenReturn(false);
            Mockito.when(credentialStore.credentialExists(date, password)).thenReturn(false);
            CredentialValidator credentialValidator = new CredentialValidator(date, password, credentialStore);


            UserRegistration userRegistration = new UserRegistration();
            userRegistration.register(date, password, credentialStore, credentialValidator);

            Mockito.verify(credentialStore, Mockito.times(0)).register(date, password);
        }

        @DisplayName("[UserRegistration:register] if date is not valid register method of CredentialStore is not called ")
        @Test
        void should_UserRegistration_registerWithDateNotValid_hasNotToCall_registerMethodOfCredentialStore() {
            CredentialStore credentialStore = Mockito.mock(CredentialStore.class);


            //Mockito.when(credentialStore.register(Mockito.any(), Mockito.any()));
            Date date = Mockito.mock(Date.class);
            PasswordString password = Mockito.mock(PasswordString.class);

            Mockito.when(date.validate()).thenReturn(false);
            Mockito.when(password.validate()).thenReturn(true);
            Mockito.when(credentialStore.credentialExists(date, password)).thenReturn(false);
            CredentialValidator credentialValidator = new CredentialValidator(date, password, credentialStore);


            UserRegistration userRegistration = new UserRegistration();
            userRegistration.register(date, password, credentialStore, credentialValidator);

            Mockito.verify(credentialStore, Mockito.times(0)).register(date, password);
        }

        @DisplayName("[UserRegistration:register] if the user already exists register method of CredentialStore is not called ")
        @Test
        void should_UserRegistration_registerWithAlreadyExistingUser_hasNotToCall_registerMethodOfCredentialStore() {
            CredentialStore credentialStore = Mockito.mock(CredentialStore.class);


            //Mockito.when(credentialStore.register(Mockito.any(), Mockito.any()));
            Date date = Mockito.mock(Date.class);
            PasswordString password = Mockito.mock(PasswordString.class);

            Mockito.when(date.validate()).thenReturn(true);
            Mockito.when(password.validate()).thenReturn(true);
            Mockito.when(credentialStore.credentialExists(date, password)).thenReturn(true);
            CredentialValidator credentialValidator = new CredentialValidator(date, password, credentialStore);


            UserRegistration userRegistration = new UserRegistration();
            userRegistration.register(date, password, credentialStore, credentialValidator);

            Mockito.verify(credentialStore, Mockito.times(0)).register(date, password);
        }
    }


    @Nested
    class PasswordString_and_CredentialValidator_Integration {
        @DisplayName("[UserRegistration:register] PasswordString Integration, validate returns VALIDATION_OK if all is correct")
        @Test
        void should_PasswordString_integration_has_to_work_properly_and_validate_has_to_return_VALIDATION_OK_if_passwordIsValid() {
            CredentialStore credentialStore = Mockito.mock(CredentialStore.class);
            //Mockito.when(credentialStore.register(Mockito.any(), Mockito.any()));
            Date date = Mockito.mock(Date.class);
            PasswordString password = new PasswordString("Ajsinso4.");

            Mockito.when(date.validate()).thenReturn(true);


            CredentialValidator credentialValidator = new CredentialValidator(date, password, credentialStore);
            Mockito.when(credentialStore.credentialExists(date, password)).thenReturn(true);


            UserRegistration userRegistration = new UserRegistration();
            userRegistration.register(date, password, credentialStore, credentialValidator);

            Mockito.verify(credentialStore).register(date, password);
        }

        @DisplayName("[UserRegistration:register] PasswordString Integration, validate doesn't return VALIDATION_OK if password is not valid")
        @Test
        void should_PasswordString_integration_has_to_work_properly_and_validate_hasNot_to_return_VALIDATION_OK_if_passwordIsInvalid() {
            CredentialStore credentialStore = Mockito.mock(CredentialStore.class);
            //Mockito.when(credentialStore.register(Mockito.any(), Mockito.any()));
            Date date = Mockito.mock(Date.class);
            PasswordString password = new PasswordString("aa.");

            Mockito.when(date.validate()).thenReturn(true);


            CredentialValidator credentialValidator = new CredentialValidator(date, password, credentialStore);
            Mockito.when(credentialStore.credentialExists(date, password)).thenReturn(true);


            UserRegistration userRegistration = new UserRegistration();
            userRegistration.register(date, password, credentialStore, credentialValidator);

            Mockito.verify(credentialStore, Mockito.times(0)).register(date, password);
        }
    }

    @Nested
    class Date_and_PasswordString_and_CredentialValidator_Integration {
        @DisplayName("[UserRegistration:register] Date Integration, validate returns VALIDATION_OK if all is correct")
        @Test
        void should_Date_integration_has_to_work_properly_and_validate_has_to_return_VALIDATION_OK_if_DateIsValid() {
            CredentialStore credentialStore = Mockito.mock(CredentialStore.class);
            //Mockito.when(credentialStore.register(Mockito.any(), Mockito.any()));
            Date date = new Date(22, 11, 2000);
            PasswordString password = new PasswordString("Ajsinso4.");


            CredentialValidator credentialValidator = new CredentialValidator(date, password, credentialStore);
            Mockito.when(credentialStore.credentialExists(date, password)).thenReturn(false);


            UserRegistration userRegistration = new UserRegistration();
            userRegistration.register(date, password, credentialStore, credentialValidator);

            Mockito.verify(credentialStore).register(date, password);
        }

        @DisplayName("[UserRegistration:register] Date Integration, validate doesn't return VALIDATION_OK if all date is not valid")
        @Test
        void should_Date_integration_has_to_work_properly_and_validate_hasNot_to_return_VALIDATION_OK_if_DateIsInvalid() {
            CredentialStore credentialStore = Mockito.mock(CredentialStore.class);
            //Mockito.when(credentialStore.register(Mockito.any(), Mockito.any()));
            Date date = new Date(22, 11, 1800);
            PasswordString password = new PasswordString("Ajsinso4.");


            CredentialValidator credentialValidator = new CredentialValidator(date, password, credentialStore);
            Mockito.when(credentialStore.credentialExists(date, password)).thenReturn(false);


            UserRegistration userRegistration = new UserRegistration();
            userRegistration.register(date, password, credentialStore, credentialValidator);

            Mockito.verify(credentialStore, Mockito.times(0)).register(date, password);
        }


    }

    @Nested
    class CredentialStore_and_Date_and_PasswordString_and_CredentialValidator_Integration {
        @DisplayName("[UserRegistration:register] CredentialStore Integration, validate returns VALIDATION_OK if all is correct and the user is stored")
        @Test
        void should_CredentialStore_integration_has_to_work_properly_and_validate_has_to_return_VALIDATION_OK_if_UserIsNotStoredYet() {
            CredentialStore credentialStore = new CredentialStoreSet();
            //Mockito.when(credentialStore.register(Mockito.any(), Mockito.any()));
            Date date = new Date(22, 11, 2000);
            PasswordString password = new PasswordString("Ajsinso4.");


            CredentialValidator credentialValidator = new CredentialValidator(date, password, credentialStore);

            UserRegistration userRegistration = new UserRegistration();
            userRegistration.register(date, password, credentialStore, credentialValidator);

            int expectedUsersList = 1;
            int obtainedUsersList = credentialStore.size();

            assertThat(obtainedUsersList).isEqualTo(expectedUsersList);
        }

        @DisplayName("[UserRegistration:register] CredentialStore Integration, validate returns VALIDATION_OK if all is correct and the user is stored")
        @Test
        void should_CredentialStore_integration_has_to_work_properly_and_validate_hasNot_to_return_VALIDATION_OK_if_UserIsStoredYet() {
            CredentialStore credentialStore = new CredentialStoreSet();
            //Mockito.when(credentialStore.register(Mockito.any(), Mockito.any()));
            Date date = new Date(22, 11, 2000);
            PasswordString password = new PasswordString("Ajsinso4.");


            CredentialValidator credentialValidator = new CredentialValidator(date, password, credentialStore);

            UserRegistration userRegistration = new UserRegistration();
            userRegistration.register(date, password, credentialStore, credentialValidator);
            /**This will not increase the users set size because it is already present in the set*/
            userRegistration.register(date, password, credentialStore, credentialValidator);


            int expectedUsersList = 1;
            int obtainedUsersList = credentialStore.size();

            assertThat(obtainedUsersList).isEqualTo(expectedUsersList);
        }


    }


}
