
--CREATE TABLE Positions (
--    position_id INT PRIMARY KEY,
--    position_name VARCHAR(50)
--);


--CREATE TABLE Employees (
--    employee_id INT PRIMARY KEY,
--    name VARCHAR(50),
--    age INT,
--    position_id INT,
--    salary DECIMAL(10, 2),
--    FOREIGN KEY (position_id) REFERENCES Positions(position_id)
--);




--CREATE TABLE Projects (
--    project_id INT PRIMARY KEY,
--    project_name VARCHAR(50),
--    budget DECIMAL(12, 2)
--);


--CREATE TABLE SalaryGrades (
--    salary_grade_id INT PRIMARY KEY,
--    salary_rate DECIMAL(10, 2)
--);


--CREATE TABLE Employee_Projects (
--    employee_id INT,
--    project_id INT,
--    PRIMARY KEY (employee_id, project_id),
--    FOREIGN KEY (employee_id) REFERENCES Employees(employee_id),
--    FOREIGN KEY (project_id) REFERENCES Projects(project_id)
--);




SELECT e.name AS EmployeeName, p.project_name AS ProjectName
FROM Employees as e
JOIN Employee_Projects as ep ON e.employee_id = ep.employee_id
JOIN Projects as p ON ep.project_id = p.project_id
WHERE e.employee_id IN (
    SELECT employee_id
    FROM Employee_Projects
    GROUP BY employee_id
    HAVING COUNT(project_id) >= 2
);



SELECT p.position_name AS PositionName, AVG(s.salary_rate) AS AvgSalaryRate
FROM Positions as p
JOIN Employees as e ON p.position_id = e.position_id
JOIN SalaryGrades as s ON e.salary = s.salary_rate
GROUP BY p.position_id, p.position_name
ORDER BY AvgSalaryRate ASC;



BEGIN TRANSACTION;

UPDATE Employees
SET salary = salary +(salary *0.1)

WHERE employee_id IN (
    SELECT ep.employee_id
    FROM Employee_Projects as ep
    JOIN Projects as p ON ep.project_id = p.project_id
    WHERE p.budget > 100000
);

COMMIT;



BEGIN TRANSACTION;

DELETE FROM Employees
WHERE age > 40 AND employee_id NOT IN (
    SELECT employee_id
    FROM Employee_Projects
);

COMMIT;