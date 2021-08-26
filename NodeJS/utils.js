export function serialize(obj) {
    let str = [];
    for (let p in obj)
        if (obj.hasOwnProperty(p)) {
            str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
        }
    return str.join("&");
}

export const getDate = (day, month, year) => {
    let ymdFormat = year + "-" + month + "-" + day;
    return new Date(ymdFormat).toISOString().substring(0, 10);;
}

export const getDateByYMDHMFormat = (day, month, year) => {
    let ymdFormat = year + "-" + month + "-" + day;
    return new Date(ymdFormat).toISOString().substring(0, 16).split("T").join(" ").replace(/[-]/g, "/");
}

export const BASE_URL = "http://localhost:8080"