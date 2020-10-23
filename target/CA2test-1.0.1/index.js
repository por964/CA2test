const url = "https://denkoldehane.dk/CA2/api/person/";

function makeOptions(method, body) {
    var opts = {
        method: method,
        headers: {
            "Content-type": "application/json",
            "Accept": "application/json"
        }
    }
    if (body) {
        opts.body = JSON.stringify(body);
    }
    return opts;
}
;

const addUser = async () => {
    //modal.style.display = "block";
    const data = {email: document.getElementById('email').value, fName: document.getElementById('fName').value,
        lName: document.getElementById('lName').value, street: document.getElementById('street').value,
        zip: document.getElementById('zip').value, hobbyName: document.getElementById('hobbyName').value,
        phNumber: document.getElementById('phNumber').value};
    const options = makeOptions("POST", data);
    fetch(url, options);
    document.getElementById('email').value = "";
    document.getElementById('fName').value = "";
    document.getElementById('lName').value = "";
    document.getElementById('street').value = "";
    document.getElementById('zip').value = "";
    document.getElementById('hobbyName').value = "";
    document.getElementById('phNumber').value = "";
};


document.getElementById('myBtn').addEventListener("click", addUser);


function fetchWithErrorCheck(res) {
    if (!res.ok) {
        return Promise.reject({status: res.status, fullError: res.json()});
    }
    return res.json();
}