-- Create Database
CREATE DATABASE IF NOT EXISTS event_management;
USE event_management;

-- Create Events Table
CREATE TABLE IF NOT EXISTS events (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    date DATE NOT NULL,
    venue VARCHAR(100) NOT NULL,
    seats_available INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_date (date),
    INDEX idx_name (name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Create Attendees Table
CREATE TABLE IF NOT EXISTS attendees (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    event_id BIGINT NOT NULL,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (event_id) REFERENCES events(id) ON DELETE CASCADE ON UPDATE CASCADE,
    INDEX idx_event_id (event_id),
    INDEX idx_email (email),
    UNIQUE KEY unique_attendee_email_event (email, event_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Sample Data (optional)
INSERT INTO events (name, date, venue, seats_available) VALUES
('New Year Concert', '2024-01-01', 'Grand Hall', 500),
('Tech Conference', '2024-02-15', 'Convention Center', 1000),
('Charity Gala', '2024-03-20', 'Ballroom A', 300),
('Educational Seminar', '2024-04-10', 'Auditorium', 200);

INSERT INTO attendees (event_id, name, email) VALUES
(1, 'Alice Johnson', 'alice@example.com'),
(1, 'Bob Smith', 'bob@example.com'),
(2, 'Charlie Brown', 'charlie@example.com'),
(2, 'Diana Prince', 'diana@example.com'),
(3, 'Eve Wilson', 'eve@example.com'),
(4, 'Frank Castle', 'frank@example.com');
