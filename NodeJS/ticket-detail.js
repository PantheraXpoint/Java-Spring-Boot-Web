const TicketDetailUrl = 'http://localhost:8080/ticket/detail';
import {getDateByYMDHMFormat} from "./utils.js"


// event listener
let returnBtn = document.getElementById("return-btn");
returnBtn.addEventListener("click",returnTicket);

function serialize(obj) {
    let str = [];
    for (let p in obj)
        if (obj.hasOwnProperty(p)) {
            str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
        }
    return str.join("&");
}

// const onFormSubmit = (event) => {
//     event.preventDefault();
//     let fullName = document.getElementById("name-input").value;
//     let phoneNumber = document.getElementById("phone-input").value;
//     let customerId = document.getElementById("customer-input").value;
//     handleSearchTicketHistory({fullName,phoneNumber,customerId});
// }

// const rewriteTable = (data) => {
//     let obj = document.getElementById("table-data");
//     let tbodys = obj.getElementsByTagName("tbody");
//     // convert html collection to array;
//     tbodys = Array.from(tbodys);
//     tbodys.forEach(body => body.innerHTML="")
//     data.forEach(element => {
//         obj.innerHTML += `
//         <tbody>
//         <tr>
//             <td><a href="#">${element.id}</a></td>
//             <td>${element.fullName}</td>
//             <td>${element.type}</td>
//             <td>${element.gender}</td>
//             <td>${element.phoneNumber}</td>
//             <td>${element.position}</td>
//         </tr>
//         </tbody>`
//     });
// }
// const handleSearchCustomer = async ({ticketId}) => {  
//     let searchData = await searchCustomer({ticketId});
//     console.log(searchData)
//     rewriteTable(searchData)
// }
// document.addEventListener('submit',onFormSubmit);

// const searchCustomer = async ({ticketId}) => {
//     let res = await fetch(`${ticketUrl}/customer/search?${serialize({ticketId})}`);
//     let data = await res.json();
//     return data;
// }


const parseYMDDate = (date,delimiter="/") => {
    const [year,month,day] = date.split(delimiter);
    return getDateByYMDHMFormat(day,month,year);
}

const fetchTicketDetail = async function ({ticketId}) {
    let res = [];
    if (ticketId)
     {
        console.log("OK")
         res = await fetch(`${TicketDetailUrl}?ticketId=${ticketId}`);
    }
    else res = await fetch(`${TicketDetailUrl}`);
    let data = await res.json();
    if (data.length && data.length > 1)
    {
        let reducedData = {...data[0],lockerCode: data.map(ticket => ticket.lockerCode)}
        console.log(reducedData);
        document.getElementById("lockerCode-input").value = reducedData.lockerCode.join(",");
        document.getElementById("ticketId-input").value = reducedData.ticketId;
        document.getElementById("customerId-input").value = reducedData.customerId;
        document.getElementById("fullName-input").value = reducedData.fullName;
        document.getElementById("type-input").value = reducedData.type;
        document.getElementById("gender-input").value = reducedData.gender;
        document.getElementById("phone-input").value = reducedData.phoneNumber;
        document.getElementById("remark-input").value = reducedData.remark;
        document.getElementById("startDate-input").value = parseYMDDate(reducedData.startDateTime);
        document.getElementById("endDate-input").value = parseYMDDate(reducedData.endDateTime);
    }
    // document.getElementById("ticketId-input").value = data
}

function returnTicket() {
    let id = document.getElementById("ticketId-input").value;
    if (id != null){
        let endDate = document.getElementById("endDate-input").value;
        console.log(endDate)
        if (endDate == ""){
            
        }
        else window.alert("Error: Ticket has already been returned")
    }
}


// Update those fields 
// Full name
// Gender	
// Phone number
// Locker code	
// Remark

// get ticketId param from url

var url_string = window.location.href;
var url = new URL(url_string);
var c = url.searchParams.get("ticketId");
console.log(c);

fetchTicketDetail({ticketId: c});
// fetchAPiTicketHistory();