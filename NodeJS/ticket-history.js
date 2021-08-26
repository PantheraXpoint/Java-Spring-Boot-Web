const ticketUrl = 'http://localhost:8080/ticket';

function serialize(obj) {
    let str = [];
    for (let p in obj)
        if (obj.hasOwnProperty(p)) {
            str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
        }
    return str.join("&");
}

const getDate = (day,month,year) => {
    let ymdFormat = year+"-"+month+"-"+day;
    return new Date(ymdFormat).toISOString().substring(0,10);;
}
const onFormSubmit = (event) => {
    event.preventDefault();
    let mode = document.getElementById("customer-input").value;
    let startDateTime = document.getElementById("start-date-input").value;
    let toDateTime = document.getElementById("to-date-input").value;
    if (startDateTime && startDateTime.length)
    {let [day,month,year] = startDateTime.split("-");
    startDateTime = getDate(day,month,year);}
    if (toDateTime && toDateTime.length)
    {
        [day,month,year] = toDateTime.split("-");
    toDateTime = getDate(day,month,year);
    }
    // console.log(startDateTime,toDateTime)
    handleSearchTicketHistory({mode, startDateTime, toDateTime});
}

const rewriteTable = (data) => {
    let obj = document.getElementById("table-data");
    let tbodys = obj.getElementsByTagName("tbody");
    // convert html collection to array;
    tbodys = Array.from(tbodys);
    tbodys.forEach(body => body.innerHTML="")
    data.forEach(element => {
        obj.innerHTML += `
        <tbody>
        <tr>
            <td><a href="/ticket-detail?ticketId=${element.ticketId}">${element.ticketId}</a></td>
            <td><a href="#">${element.customerId}</a></td>
            <td>${element.fullName}</td>
            <td>${element.lockerCode}</td>
            <td>${element.lockerName}</td>
            <td>${element.startDateTime}</td>
            <td>${element.endDateTime}</td>
        </tr>
        </tbody>`
    });
}
const handleSearchTicketHistory = async ({mode,startDateTime,toDateTime}) => {  
    // console.log(mode,startDateTime,toDateTime)
    let searchData = await searchTicketHistory({mode,startDateTime,toDateTime});
    console.log(searchData);
    rewriteTable(searchData)
}
document.addEventListener('submit',onFormSubmit);



const fetchTicketHistory = async function ({customerId}) {
    let res = [];
    if (customerId)
     res = await fetch(`${ticketUrl}/history?customerId=${customerId}`);
    else res = await fetch(`${ticketUrl}/history`);
    let data = await res.json();
    let obj = document.getElementById("table-data");
    // obj.innerHTML+="<tbody>"
    data.forEach(element => {
        obj.innerHTML +=`
        <tr>
            <td><a href="/ticket-detail?ticketId=${element.ticketId}">${element.ticketId}</a></td>
            <td><a href="#">${element.customerId}</a></td>
            <td>${element.fullName}</td>
            <td>${element.lockerCode}</td>
            <td>${element.lockerName}</td>
            <td>${element.startDateTime}</td>
            <td>${element.endDateTime}</td>
        </tr>`
    }
    );
    // obj.innerHTML+="</tbody>";
}

const searchData = () => {
    let searchVal = document.getElementById("search-input").value;
    console.log(searchVal);
}
const searchTicketHistory = async function ({mode,startDateTime,toDateTime}) {
    let res = await fetch(`${ticketUrl}/history/search?${serialize({mode,startDateTime,toDateTime})}`);
    let data = await res.json();
    return data;
}


var url_string = window.location.href;
var url = new URL(url_string);
var c = url.searchParams.get("customerId");
console.log(c);

// fetchAPiCustomer();
fetchTicketHistory({customerId: c});