package fr.insee.keycloak.providers.agentconnect;

/**
 * How strongly the ProConnect (AgentConnect) broker asks for and enforces MFA-flavored ACR
 * values (eidas0-mfa, eidas1-mfa, eidas2, eidas3) on top of the configured eIDAS level.
 */
enum MfaRequirement {
  /** No MFA is requested. An MFA-flavored ACR is still accepted if the user happens to return one. */
  DISABLED,
  /** MFA is requested as a preference (claims essential=false). Users without MFA still succeed. */
  OPTIONAL,
  /** MFA is requested as a hard requirement (claims essential=true). Non-MFA ACR values are rejected. */
  REQUIRED;

  public static final String MFA_MODE_PROPERTY_NAME = "mfa_mode";

  @Override
  public String toString() {
    return name().toLowerCase();
  }

  public static MfaRequirement getOrDefault(String mfaRequirementName, MfaRequirement defaultMfaRequirement) {
    for (var mfaRequirement : MfaRequirement.values()) {
      if (mfaRequirement.name().equalsIgnoreCase(mfaRequirementName)) {
        return mfaRequirement;
      }
    }

    return defaultMfaRequirement;
  }
}
