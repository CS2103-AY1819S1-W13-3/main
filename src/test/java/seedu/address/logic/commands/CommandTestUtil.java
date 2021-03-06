package seedu.address.logic.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FACULTY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BODY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.TypicalEvents.EVENT_NOT_PRESENT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.event.Event;
import seedu.address.model.student.NameContainsKeywordsPredicate;
import seedu.address.model.student.Student;
import seedu.address.testutil.EditStudentDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_STUDENT_NUMBER_AMY = "A11111111";
    public static final String VALID_STUDENT_NUMBER_BOB = "A22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "School of Computing";
    public static final String VALID_FACULTY_BOB = "Faculty of Science";
    public static final String VALID_TAG_PHYSICS = "physics";
    public static final String VALID_TAG_MATH = "math";
    public static final String VALID_ATTENDANCE_AMY = "0";
    public static final String VALID_ATTENDANCE_BOB = "0";
    public static final String VALID_SUBJECT_EMAIL = "Subject";
    public static final String VALID_BODY_EMAIL = "Body";
    public static final String VALID_INDEX_AMY = "1";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String STUDENT_NUMBER_DESC_AMY = " " + PREFIX_STUDENT_NUMBER + VALID_STUDENT_NUMBER_AMY;
    public static final String STUDENT_NUMBER_DESC_BOB = " " + PREFIX_STUDENT_NUMBER + VALID_STUDENT_NUMBER_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String FACULTY_DESC_AMY = " " + PREFIX_FACULTY + VALID_ADDRESS_AMY;
    public static final String FACULTY_DESC_BOB = " " + PREFIX_FACULTY + VALID_FACULTY_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_MATH;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_PHYSICS;
    public static final String SUBJECT_DESC = " " + PREFIX_SUBJECT + VALID_SUBJECT_EMAIL;
    public static final String BODY_DESC = " " + PREFIX_BODY + VALID_BODY_EMAIL;
    public static final String INDEX_DESC_AMY = " " + VALID_INDEX_AMY;


    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_STUDENT_NUMBER_DESC = " "
            + PREFIX_STUDENT_NUMBER + "911*a"; // 'a' not allowed in student numbers
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_FACULTY_DESC = " " + PREFIX_FACULTY; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "nope*"; // '*' not allowed in tags
    public static final String INVALID_SUBJECT_DESC = " " + PREFIX_SUBJECT; //empty string not allowed for subject
    public static final String INVALID_BODY_DESC = " " + PREFIX_BODY; //empty string not allowed for body of email
    public static final String INVALID_INDEX_DESC = " " + "1a"; //'a' not allowed in index

    public static final String VALID_EVENT_NAME_TUTORIAL = "Tutorial";
    public static final String VALID_EVENT_NAME_CONSULTATION = "Consultation";
    public static final String VALID_DATE_TUTORIAL = "24-10-2019";
    public static final String VALID_DATE_CONSULTATION = "01-11-2019";
    public static final String VALID_START_TIME_TUTORIAL = "13:00";
    public static final String VALID_START_TIME_CONSULTATION = "10:00";
    public static final String VALID_END_TIME_TUTORIAL = "14:00";
    public static final String VALID_END_TIME_CONSULTATION = "10:30";
    public static final String VALID_DESCRIPTION_TUTORIAL = "Prepare tutorial answers";
    public static final String VALID_DESCRIPTION_CONSULTATION = "Review midterm script";

    public static final String EVENT_NAME_DESC_TUTORIAL = " " + PREFIX_EVENT_NAME + VALID_EVENT_NAME_TUTORIAL;
    public static final String EVENT_NAME_DESC_CONSULTATION = " " + PREFIX_EVENT_NAME + VALID_EVENT_NAME_CONSULTATION;
    public static final String DATE_DESC_TUTORIAL = " " + PREFIX_DATE + VALID_DATE_TUTORIAL;
    public static final String DATE_DESC_CONSULTATION = " " + PREFIX_DATE + VALID_DATE_CONSULTATION;
    public static final String START_TIME_DESC_TUTORIAL = " " + PREFIX_START + VALID_START_TIME_TUTORIAL;
    public static final String START_TIME_DESC_CONSULTATION = " " + PREFIX_START + VALID_START_TIME_CONSULTATION;
    public static final String END_TIME_DESC_TUTORIAL = " " + PREFIX_END + VALID_END_TIME_TUTORIAL;
    public static final String END_TIME_DESC_CONSULTATION = " " + PREFIX_END + VALID_END_TIME_CONSULTATION;
    public static final String DESCRIPTION_DESC_TUTORIAL = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_TUTORIAL;
    public static final String DESCRIPTION_DESC_CONSULTATION = " " + PREFIX_DESCRIPTION
            + VALID_DESCRIPTION_CONSULTATION;

    public static final String INVALID_EVENT_NAME_DESC = " " + PREFIX_EVENT_NAME;
    public static final String INVALID_DATE_DESC = " " + PREFIX_DATE + "12122018";
    public static final String INVALID_START_TIME_DESC = " " + PREFIX_START + "1000";
    public static final String INVALID_END_TIME_DESC = " " + PREFIX_END + "25:00";
    public static final String INVALID_DESCRIPTION_DESC = " " + PREFIX_DESCRIPTION;

    public static final String INVALID_START_AND_END_DESC =
            " " + PREFIX_START + VALID_END_TIME_TUTORIAL
            + " " + PREFIX_END + VALID_START_TIME_TUTORIAL;

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditStudentDescriptor DESC_AMY;
    public static final EditCommand.EditStudentDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditStudentDescriptorBuilder().withName(VALID_NAME_AMY)
                .withStudentNumber(VALID_STUDENT_NUMBER_AMY).withEmail(VALID_EMAIL_AMY).withFaculty(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_MATH).build();
        DESC_BOB = new EditStudentDescriptorBuilder().withName(VALID_NAME_BOB)
                .withStudentNumber(VALID_STUDENT_NUMBER_BOB).withEmail(VALID_EMAIL_BOB).withFaculty(VALID_FACULTY_BOB)
                .withTags(VALID_TAG_PHYSICS, VALID_TAG_MATH).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the result message matches {@code expectedMessage} <br>
     * - the {@code actualModel} matches {@code expectedModel} <br>
     * - the {@code actualCommandHistory} remains unchanged.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandHistory actualCommandHistory,
            String expectedMessage, Model expectedModel) {
        CommandHistory expectedCommandHistory = new CommandHistory(actualCommandHistory);
        try {
            CommandResult result = command.execute(actualModel, actualCommandHistory);
            assertEquals(expectedMessage, result.feedbackToUser);
            assertEquals(expectedModel, actualModel);
            assertEquals(expectedCommandHistory, actualCommandHistory);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book and the filtered student list in the {@code actualModel} remain unchanged <br>
     * - {@code actualCommandHistory} remains unchanged.
     */
    public static void assertCommandFailure(Command command, Model actualModel, CommandHistory actualCommandHistory,
            String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Student> expectedFilteredList = new ArrayList<>(actualModel.getFilteredStudentList());

        CommandHistory expectedCommandHistory = new CommandHistory(actualCommandHistory);

        try {
            command.execute(actualModel, actualCommandHistory);
            throw new AssertionError("The expected CommandException was not thrown.");
        } catch (CommandException e) {
            assertEquals(expectedMessage, e.getMessage());
            assertEquals(expectedAddressBook, actualModel.getAddressBook());
            assertEquals(expectedFilteredList, actualModel.getFilteredStudentList());
            assertEquals(expectedCommandHistory, actualCommandHistory);
        }
    }

    /**
     * Updates {@code model}'s filtered list to show only the student at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showStudentAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredStudentList().size());

        Student student = model.getFilteredStudentList().get(targetIndex.getZeroBased());
        final String[] splitName = student.getName().fullName.split("\\s+");
        model.updateFilteredStudentList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredStudentList().size());
    }

    /**
     * Deletes the first student in {@code model}'s filtered list from {@code model}'s address book.
     */
    public static void deleteFirstStudent(Model model) {
        Student firstStudent = model.getFilteredStudentList().get(0);
        model.deleteStudent(firstStudent);
        model.commitAddressBook();
    }

    /**
     * Deletes the first event in {@code model}'s filtered list from {@code model}'s calendar.
     */
    public static void deleteFirstEvent(Model model) {
        Event firstEvent = model.getFilteredEventList().get(0);
        model.deleteEvent(firstEvent);
        model.commitCalendar();
    }

    /**
     * Adds a typical event to {@code model}'s calendar.
     */
    public static void addTypicalEvent(Model model) {
        model.addEvent(EVENT_NOT_PRESENT);
        model.commitCalendar();
    }

}
