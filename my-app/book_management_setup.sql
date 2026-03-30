-- =====================================================
-- DATABASE SETUP FOR BOOK MANAGEMENT SYSTEM
-- =====================================================

-- Create the database
CREATE DATABASE IF NOT EXISTS book_management;
USE book_management;

-- =====================================================
-- USERS TABLE
-- =====================================================
CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100),
    full_name VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_username (username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- =====================================================
-- CATEGORIES TABLE
-- =====================================================
CREATE TABLE IF NOT EXISTS categories (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    description VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_name (name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- =====================================================
-- BOOKS TABLE
-- =====================================================
CREATE TABLE IF NOT EXISTS books (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(100),
    category_id BIGINT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    description VARCHAR(500),
    isbn VARCHAR(20),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE CASCADE ON UPDATE CASCADE,
    INDEX idx_title (title),
    INDEX idx_author (author),
    INDEX idx_category (category_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- =====================================================
-- INSERT SAMPLE USER DATA
-- =====================================================
INSERT INTO users (username, password, email, full_name) VALUES
('admin', 'password', 'admin@bookstore.com', 'Administrator'),
('john', 'john123', 'john@bookstore.com', 'John Doe'),
('jane', 'jane123', 'jane@bookstore.com', 'Jane Smith');

-- =====================================================
-- INSERT SAMPLE CATEGORY DATA
-- =====================================================
INSERT INTO categories (name, description) VALUES
('Fiction', 'Fictional novels and stories'),
('Non-Fiction', 'Educational and reference books'),
('Science', 'Science and technology books'),
('History', 'Historical books and chronicles'),
('Self-Help', 'Personal development and self-help books'),
('Travel', 'Travel guides and adventure stories'),
('Biography', 'Biographies and memoirs');

-- =====================================================
-- INSERT SAMPLE BOOK DATA
-- =====================================================
INSERT INTO books (title, author, category_id, price, description, isbn) VALUES
('The Great Gatsby', 'F. Scott Fitzgerald', 1, 12.99, 'A classic American novel set in the Jazz Age', '978-0743273565'),
('To Kill a Mockingbird', 'Harper Lee', 1, 14.99, 'A gripping tale of racial injustice and childhood innocence', '978-0061120084'),
('1984', 'George Orwell', 1, 13.99, 'A dystopian novel about totalitarianism', '978-0451524935'),
('Sapiens', 'Yuval Noah Harari', 2, 18.99, 'A brief history of humankind', '978-0062316097'),
('Thinking, Fast and Slow', 'Daniel Kahneman', 2, 16.99, 'Insights into the two systems of human thought', '978-0374275631'),
('The Brief History of Time', 'Stephen Hawking', 3, 15.99, 'An exploration of black holes and the universe', '978-0553380163'),
('Guns, Germs, and Steel', 'Jared Diamond', 4, 17.99, 'Why some civilizations advanced and others did not', '978-0393317558'),
('The 7 Habits of Highly Effective People', 'Stephen Covey', 5, 14.99, 'A guide to personal effectiveness', '978-0743269513'),
('In Search of Lost Time', 'Marcel Proust', 1, 25.99, 'A monumental work of world literature', '978-0142437902'),
('The Art of Travel', 'Alain de Botton', 6, 13.99, 'Reflections on the beauty of exploring new places', '978-0375724497');

-- =====================================================
-- SAMPLE QUERIES FOR VERIFICATION
-- =====================================================
-- SELECT * FROM users;
-- SELECT * FROM categories;
-- SELECT b.id, b.title, b.author, c.name as category, b.price FROM books b JOIN categories c ON b.category_id = c.id;
