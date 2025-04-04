package es.pruebaMarcos.demo.test;

import org.springframework.web.bind.annotation.*;

import es.pruebaMarcos.demo.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@RestController
@RequestMapping("/v2/books")
public class BookController {

    private List<Book> library = new ArrayList<>();
    //private List<BorrowRecord> borrowRecords = new ArrayList<>();

    @PostMapping("/add")
    public String addBook(@RequestBody Book book) {
        library.add(book);
        return "Book added successfully";
    }

    @PutMapping("/update/{title}")
    public String updateBook(@PathVariable String title, @RequestBody Book updatedBook) {
        Optional<Book> bookOpt = library.stream()
                .filter(b -> b.getTitle().equalsIgnoreCase(title))
                .findFirst();

        if (bookOpt.isPresent()) {
            Book book = bookOpt.get();
            //book.setAuthor(updatedBook.getAuthor());
            book.setAvailable(updatedBook.isAvailable());
            return "Book updated successfully";
        } else {
            return "Book not found";
        }
    }

    @DeleteMapping("/remove/{title}")
    public String removeBook(@PathVariable String title) {
        boolean removed = library.removeIf(book -> book.getTitle().equalsIgnoreCase(title));
        return removed ? "Book removed successfully" : "Book not found";
    }

    @GetMapping("/list")
    public List<Book> listBooks(@RequestParam(required = false) String titlePattern) {
        if (titlePattern != null) {
            return library.stream()
                    .filter(b -> b.getTitle().toLowerCase().contains(titlePattern.toLowerCase()))
                    .toList();
        }
        return library;
    }

    @GetMapping("/available")
    public List<Book> filterAvailableBooks() {
        return library.stream()
                .filter(Book::isAvailable)
                .toList();
    }

    @PostMapping("/borrow/{username}/{title}")
    public String borrowBook(@PathVariable String username, @PathVariable String title) {
        Optional<Book> bookOpt = library.stream()
                .filter(b -> b.getTitle().equalsIgnoreCase(title) && b.isAvailable())
                .findFirst();

        if (bookOpt.isPresent()) {
            Book book = bookOpt.get();
            book.setAvailable(false);
            //BorrowRecord record = new BorrowRecord(username, book, LocalDate.now(), LocalDate.now().plus(14, ChronoUnit.DAYS));
            //borrowRecords.add(record);
            return "Book borrowed successfully for two weeks";
        } else {
            return "Book not available for borrowing";
        }
    }

/*     @PostMapping("/return/{username}/{title}")
    public String returnBook(@PathVariable String username, @PathVariable String title) {
        //Optional<BorrowRecord> recordOpt = borrowRecords.stream()
                //.filter(r -> r.getUsername().equals(username) && r.getBook().getTitle().equalsIgnoreCase(title))
                //.findFirst();

        if (recordOpt.isPresent()) {
            BorrowRecord record = recordOpt.get();
            LocalDate returnDate = LocalDate.now();
            long daysLate = ChronoUnit.DAYS.between(record.getDueDate(), returnDate);
            if (daysLate > 0) {
                return "Book returned late. Penalty applied.";
            }
            library.stream()
                    .filter(b -> b.getTitle().equalsIgnoreCase(title))
                    .findFirst()
                    .ifPresent(b -> b.setAvailable(true));
            borrowRecords.remove(record);
            return "Book returned successfully";
        } else {
            return "Borrow record not found";
        }
    } */

/*     @PostMapping("/extend/{username}/{title}")
    public String extendBorrowing(@PathVariable String username, @PathVariable String title) {
        Optional<BorrowRecord> recordOpt = borrowRecords.stream()
                .filter(r -> r.getUsername().equals(username) && r.getBook().getTitle().equalsIgnoreCase(title))
                .findFirst();

        if (recordOpt.isPresent()) {
            BorrowRecord record = recordOpt.get();
            record.setDueDate(record.getDueDate().plus(7, ChronoUnit.DAYS));
            return "Borrowing period extended by one week";
        } else {
            return "Borrow record not found";
        }
    }

    @GetMapping("/current-loans")
    public List<BorrowRecord> getCurrentLoans(@RequestParam(required = false) LocalDate startDate, @RequestParam(required = false) LocalDate endDate) {
        return borrowRecords.stream()
                .filter(r -> (startDate == null || !r.getBorrowDate().isBefore(startDate)) &&
                             (endDate == null || !r.getBorrowDate().isAfter(endDate)))
                .toList();
    }

    @GetMapping("/history/{username}")
    public List<BorrowRecord> getBorrowingHistory(@PathVariable String username) {
        return borrowRecords.stream()
                .filter(r -> r.getUsername().equals(username))
                .toList();
    }

    @GetMapping("/user-activity/{username}")
    public UserActivity getUserActivity(@PathVariable String username) {
        List<BorrowRecord> currentLoans = getBorrowingHistory(username);
        List<BorrowRecord> lastReturned = borrowRecords.stream()
                .filter(r -> r.getUsername().equals(username))
                .sorted((r1, r2) -> r2.getReturnDate().compareTo(r1.getReturnDate()))
                .limit(5)
                .toList();

        return new UserActivity(currentLoans, lastReturned);
    } */
}