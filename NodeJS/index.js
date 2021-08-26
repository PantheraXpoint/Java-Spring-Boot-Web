import { serialize, BASE_URL } from "./utils.js"

const onFormSubmit = (event) => {
    event.preventDefault();
    let fullName = document.getElementById("name-input").value;
    let phoneNumber = document.getElementById("phone-input").value;
    let customerId = document.getElementById("customer-input").value;
    handleSearchCustomer({ fullName, phoneNumber, customerId });
}

const rewriteTable = (data) => {
    let obj = document.getElementById("table-data");
    let tbodys = obj.getElementsByTagName("tbody");
    // convert html collection to array;
    tbodys = Array.from(tbodys);
    tbodys.forEach(body => body.innerHTML = "")
    data.forEach(element => {
        obj.innerHTML += `
        <tbody>
        <tr>
            <td><a href="/ticket-history?customerId=${element.id}">${element.id}</a></td>
            <td>${element.fullName}</td>
            <td>${element.type}</td>
            <td>${element.gender}</td>
            <td>${element.phoneNumber}</td>
            <td>${element.position}</td>
        </tr>
        </tbody>`
    });
}
const handleSearchCustomer = async ({ fullName, phoneNumber, customerId }) => {
    let searchData = await searchCustomer({ fullName, phoneNumber, customerId });
    console.log(searchData)
    rewriteTable(searchData)
}
document.addEventListener('submit', onFormSubmit);

const searchCustomer = async ({ fullName, phoneNumber, customerId }) => {
    let res = await fetch(`${BASE_URL}/customer/search?${serialize({ fullName, phoneNumber, customerId })}`);
    let data = await res.json();
    return data;
}

const fetchAPiCustomer = async function () {
    let res = await fetch(`${BASE_URL}/customer/all`);
    let data = await res.json();
    let obj = document.getElementById("table-data");
    // obj.innerHTML+="<tbody>"
    data.forEach(element => {
        obj.innerHTML += `
        <tr>
            <td><a href="/ticket-history?customerId=${element.id}">${element.id}</a></td>
            <td>${element.fullName}</td>
            <td>${element.type}</td>
            <td>${element.gender}</td>
            <td>${element.phoneNumber}</td>
            <td>${element.position}</td>
        </tr>`
    }
    );
    // obj.innerHTML+="</tbody>";
}

const searchData = () => {
    let searchVal = document.getElementById("search-input").value;
    console.log(searchVal);
}



fetchAPiCustomer();