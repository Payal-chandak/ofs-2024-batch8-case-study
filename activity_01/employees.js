import fs from 'fs';
import readline from 'readline-sync';


const readEmployeesFromFile = (filePath) => {
    try {
        const data = fs.readFileSync(filePath, 'utf8');
        return JSON.parse(data);
    } catch (error) {
        if (error.code === 'ENOENT') {
            // If file doesn't exist, return an empty array
            return [];
        }
        throw error;
    }
};


const writeEmployeesToFile = (filePath, employees) => {
    fs.writeFileSync(filePath, JSON.stringify(employees, null, 2), 'utf8');
};


const id = readline.question("Enter employee ID: ");
const name = readline.question("Enter employee name: ");
const salary = parseFloat(readline.question("Enter employee salary: "));

const newEmployee = {
    id: id,
    name: name,
    salary: salary
};


const filePath = 'employees.json';


const employees = readEmployeesFromFile(filePath);


employees.push(newEmployee);


writeEmployeesToFile(filePath, employees);

console.log("Employee data has been written to the file.");

const allEmployees = readEmployeesFromFile(filePath);
console.log("Current employees in the file:");
allEmployees.forEach(emp => {
    console.log(`ID: ${emp.id}, Name: ${emp.name}, Salary: ${emp.salary}`);
});
