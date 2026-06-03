async function loadDepartments() {
    const response = await fetch("/departments");

    const departments = await response.json();

    const list = document.getElementById("departments");

    list.innerHTML = "";

    departments.forEach(dept => {
        const item = document.createElement("li");

        item.textContent = dept.id + " - " + dept.name;

        list.appendChild(item);
    });
}