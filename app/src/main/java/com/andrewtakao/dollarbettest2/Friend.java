package com.andrewtakao.dollarbettest2;

/**
 * Created by andrewtakao on 10/21/16.
 */
public class Friend {
    private String name, message;
    private int requested, pending, resolved;
    private long noteId, dateCreatedMilli;
    private Category category;

    public enum Category{ PERSONAL, TECHNICAL, QUOTE, FINANCE}

    public Friend(String name, String message, Category category) {
        this.name = name;
        this.message = message;
        this.category = category;
        this.noteId = 0;
        this.dateCreatedMilli = 0;
        this.requested = 0;
        this.pending = 0;
        this.resolved = 0;
    }

    public Friend(String name, String message, Category category, long noteId, long dateCreatedMilli) {
        this.name = name;
        this.message = message;
        this.category = category;
        this.noteId = noteId;
        this.dateCreatedMilli = dateCreatedMilli;
        this.requested = 0;
        this.pending = 0;
        this.resolved = 0;
    }

    public int getRequested() {
        return requested;
    }

    public void setRequested(int requested) {
        this.requested = requested;
    }

    public int getPending() {
        return pending;
    }

    public void setPending(int pending) {
        this.pending = pending;
    }

    public int getResolved() {
        return resolved;
    }

    public void setResolved(int resolved) {
        this.resolved = resolved;
    }

    public String getName() {
        return name;
    }

    public String getRequestedBet() {
        return message;
    }

    public long getFriendId() {
        return noteId;
    }

    public long getDateCreatedMilli() {
        return dateCreatedMilli;
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "Friend{" +
                "name='" + name + '\'' +
                ", message='" + message + '\'' +
                ", noteId=" + noteId +
                ", dateCreatedMilli=" + dateCreatedMilli +
                ", category=" + category +
                '}';
    }

    public int getAssociatedDrawable(){
        return categoryToDrawable(category);
    }

    public static int categoryToDrawable(Category noteCategory){
        switch (noteCategory) {
            case PERSONAL:
                return R.drawable.p;
            case TECHNICAL:
                return R.drawable.t;
            case FINANCE:
                return R.drawable.f;
            case QUOTE:
                return R.drawable.q;
        }
    return 0;
    }

}


