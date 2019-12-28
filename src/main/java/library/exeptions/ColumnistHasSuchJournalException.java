package library.exeptions;

public class ColumnistHasSuchJournalException extends RuntimeException  {

    public ColumnistHasSuchJournalException(String columnistName, String journalTitle) {
        super("columnist '"+columnistName+"' already has journal: "+journalTitle);
    }
}
