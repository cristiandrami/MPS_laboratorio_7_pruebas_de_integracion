package org.mps.authentication;

import org.mps.authentication.CredentialValidator.ValidationStatus;

public class UserRegistration {

  private CredentialValidator credentialValidator;
  public void register(Date birthDate, PasswordString passwordString,
      CredentialStore credentialStore, CredentialValidator credentialValidator) {

    this.credentialValidator = credentialValidator;
    ValidationStatus status = credentialValidator.validate();

    if (status == ValidationStatus.VALIDATION_OK) {
      credentialStore.register(birthDate, passwordString);
    }
  }


}

