package messages;


import utils.socket.IMessage;

import java.util.Date;

public class UserCoursesRequest implements IMessage {
    private final Date date;

    public UserCoursesRequest(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return this.date;
    }
}
