package com.andrewtakao.dollarbettest2;

/**
 * Created by andrewtakao on 10/21/16.
 */
public class Bet {
    private String bet;
    private long noteId, dateCreatedMilli;
    private Category category;

    public enum Category {REQUESTED, PENDING, RESOLVED}

    public Bet(String bet, Category category) {
        this.bet = bet;
        this.category = category;
        this.noteId = 0;
        this.dateCreatedMilli = 0;
    }

    public Bet(String bet, Category category, long noteId, long dateCreatedMilli) {
        this.bet = bet;
        this.category = category;
        this.noteId = noteId;
        this.dateCreatedMilli = dateCreatedMilli;
    }

    public String getBet() {
        return bet;
    }

    public void setBet(String bet) {
        this.bet = bet;
    }

    public long getNoteId() {
        return noteId;
    }

    public void setNoteId(long noteId) {
        this.noteId = noteId;
    }

    public long getDateCreatedMilli() {
        return dateCreatedMilli;
    }

    public void setDateCreatedMilli(long dateCreatedMilli) {
        this.dateCreatedMilli = dateCreatedMilli;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Bet{" +
                "bet='" + bet + '\'' +
                ", category='" + category + '\'' +
                ", noteId=" + noteId +
                ", dateCreatedMilli=" + dateCreatedMilli +
                '}';
    }

}


