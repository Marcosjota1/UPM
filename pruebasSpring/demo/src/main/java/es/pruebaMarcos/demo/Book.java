package es.pruebaMarcos.demo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Book {
    private String title;
    private String author;
    private boolean available;
    private List<BorrowingHistory> borrowingHistory;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.available = true;
        this.borrowingHistory = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public List<BorrowingHistory> getBorrowingHistory() {
        return borrowingHistory;
    }

    public void addBorrowingHistory(BorrowingHistory history) {
        this.borrowingHistory.add(history);
    }

    public static class BorrowingHistory {
        private String user;
        private Date borrowDate;
        private Date returnDate;
        private boolean late;

        public BorrowingHistory(String user, Date borrowDate, Date returnDate, boolean late) {
            this.user = user;
            this.borrowDate = borrowDate;
            this.returnDate = returnDate;
            this.late = late;
        }

        public String getUser() {
            return user;
        }

        public Date getBorrowDate() {
            return borrowDate;
        }

        public Date getReturnDate() {
            return returnDate;
        }

        public boolean isLate() {
            return late;
        }
    }
}