SELECT p.productID, p.name, p.price, d.name AS companyName
FROM product p
LEFT JOIN distributingcompany d ON p.companyID = d.companyID;


SELECT p.productID, p.name, drug.dosage
FROM product p
JOIN drug ON p.productID = drug.productID;


SELECT p.productID, p.name, o.category
FROM product p
JOIN otheritem o ON p.productID = o.productID;





SELECT productID, name, stockQuantity
FROM product
WHERE stockQuantity < 5;




SELECT productID, name, expiryDate
FROM product
WHERE expiryDate <= DATE_ADD(CURDATE(), INTERVAL 30 DAY);




SELECT s.saleID, s.date, s.totalAmount, e.name AS employeeName, c.name AS customerName
FROM sale s
LEFT JOIN employee e ON s.employeeID = e.employeeID
LEFT JOIN customer c ON s.customerID = c.customerID;





SELECT si.productID, p.name, si.quantity, si.price
FROM sale_items si
JOIN product p ON si.productID = p.productID
WHERE si.saleID = 1;  -- بدل 1 برقم البيع اللي عايزه






SELECT SUM(totalAmount) AS totalSales
FROM sale
WHERE MONTH(date) = MONTH(CURDATE()) AND YEAR(date) = YEAR(CURDATE());





SELECT p.name, SUM(si.quantity) AS totalSold
FROM sale_items si
JOIN product p ON si.productID = p.productID
GROUP BY si.productID
ORDER BY totalSold DESC
LIMIT 5;






SELECT e.employeeID, e.name, e.role
FROM employee e
JOIN manager m ON e.employeeID = m.managerID;





