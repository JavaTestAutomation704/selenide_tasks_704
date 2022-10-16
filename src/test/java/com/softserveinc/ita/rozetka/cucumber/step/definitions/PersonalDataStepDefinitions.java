package com.softserveinc.ita.rozetka.cucumber.step.definitions;

import com.softserveinc.ita.rozetka.components.Header;
import com.softserveinc.ita.rozetka.components.profile.EditPersonalDataSection;
import com.softserveinc.ita.rozetka.components.profile.PersonalDataSection;
import com.softserveinc.ita.rozetka.data.profile.CommunicationLanguage;
import com.softserveinc.ita.rozetka.data.profile.Gender;
import com.softserveinc.ita.rozetka.models.PersonalData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.SoftAssertions;

import static com.softserveinc.ita.rozetka.cucumber.step.definitions.BaseStepDefinition.homePage;
import static com.softserveinc.ita.rozetka.utils.DateUtil.getRandomPastDate;
import static com.softserveinc.ita.rozetka.utils.RandomUtil.getRandomCyrillicString;
import static com.softserveinc.ita.rozetka.utils.RandomUtil.getRandomEnum;
import static org.assertj.core.api.Assertions.assertThat;

public class PersonalDataStepDefinitions {

    private final SoftAssertions softly = new SoftAssertions();
    private final Header header = homePage.getHeader();
    private PersonalDataSection personalDataSection;
    private PersonalData personalDataBeforeEditing;
    private EditPersonalDataSection editPersonalDataSection;
    private PersonalData personalDataAfterEditing;
    private PersonalData newPersonalData;

    @And("The user opens personal data section via main sidebar")
    public void theUserOpensPersonalDataSectionViaMainSidebar() {
        personalDataSection = header
                .openMainSidebar()
                .openProfilePage()
                .openPersonalDataSection();
    }

    @Then("Personal data section should be opened")
    public void personalDataSectionShouldBeOpened() {
        assertThat(personalDataSection.isOpened())
                .isTrue();
    }

    @When("The user starts editing by clicking on the edit button")
    public void theUserStartsEditingByClickingOnTheEditButton() {
        personalDataBeforeEditing = personalDataSection.getPersonalData();
        editPersonalDataSection = personalDataSection.startEditing();

    }

    @And("The user fills in all personal data fields random data")
    public void theUserFillsInAllPersonalDataFieldsRandomData() {
        newPersonalData = PersonalData
                .builder()
                .firstName(getRandomCyrillicString())
                .secondName(getRandomCyrillicString())
                .lastName(getRandomCyrillicString())
                .birthday(getRandomPastDate("dd-MM-yyyy"))
                .gender(getRandomEnum(Gender.class))
                .language(getRandomEnum(CommunicationLanguage.class))
                .build();
        editPersonalDataSection.fillInAllPersonalDataFields(newPersonalData);
    }

    @Then("Save button should be enabled")
    public void saveButtonShouldBeEnabled() {
        assertThat(editPersonalDataSection.isSaveButtonDisabled())
                .isFalse();
    }

    @When("The user saves changes")
    public void theUserSavesChanges() {
        editPersonalDataSection.save();
        personalDataAfterEditing = personalDataSection.getPersonalData();
    }

    @Then("Personal data should be updated after editing")
    public void personalDataShouldBeUpdatedAfterEditing() {
        softly.assertThat(personalDataAfterEditing)
                .usingRecursiveComparison()
                .isNotEqualTo(personalDataBeforeEditing);
    }

    @And("Personal data should be the same as entered data during editing\"")
    public void personalDataShouldBeTheSameAsEnteredDataDuringEditing() throws Throwable {    // Write code here that turns the phrase above into concrete actions    throw new cucumber.api.PendingException();}
        softly.assertThat(personalDataAfterEditing)
                .usingRecursiveComparison()
                .isEqualTo(newPersonalData);
    }
}