const CustomerUrl = 'http://localhost:8080';

const fetchAPiCustomer = async function () {
    let res = await fetch(`${CustomerUrl}/customer/all`);
    let data = await res.json();
    let obj = document.getElementById("table-data");
    data.forEach(element => {
        obj.innerHTML += `<tr>
            <th>${element.id}</th>
            <th>${element.fullName}</th>
            <th>${element.type}</th>
            <th>${element.gender}</th>
            <th>${element.phoneNumber}</th>
            <th>${element.position}</th>
        </tr>`
    });
}

const searchData = () => {
    let searchVal = document.getElementById("search-input").value;
    console.log(searchVal);
}
const fetchAPiTicketHistory = async function () {
    let res = await fetch(`${CustomerUrl}/ticket/history`);
    let data = await res.json();
    let obj = document.getElementById("table-data");
    data.forEach(element => {
        obj.innerHTML += `<tr>
            <th>${element.ticketId}</th>
            <th>${element.customerId}</th>
            <th>${element.fullName}</th>
            <th>${element.lockerCode}</th>
            <th>${element.lockerName}</th>
            <th>${element.startDateTime}</th>
            <th>${element.endDateTime}</th>
        </tr>`
    });
}

fetchAPiCustomer();
fetchAPiTicketHistory();