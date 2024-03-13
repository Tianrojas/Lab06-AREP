const loadGetMsg = (page) => {
    const xhttp = new XMLHttpRequest();
    xhttp.onload = function() {
        if (this.status === 200) {
            const response = JSON.parse(this.responseText);
            let logsHtml = "";
            response.forEach(log => {
                const messageId = log._id["$oid"];
                const message = decodeURIComponent(log["message: "].replace("message=", ""));
                const timestamp = new Date(parseInt(log[", timestamp: "]));
                const formattedTimestamp = formatDate(timestamp);
                logsHtml += `<div>${messageId}: ${message} - ${formattedTimestamp}</div>`;
            });
            document.getElementById("getrespmsg").innerHTML = logsHtml;
        } else {
            document.getElementById("getrespmsg").innerHTML = "<div>Error al obtener los registros</div>";
        }
    }
    const url = `http://localhost:35000/logs/${page}`;
    xhttp.open("GET", url);
    xhttp.send();
}


const formatDate = (timestamp) => {
    const options = { year: 'numeric', month: 'short', day: '2-digit', hour: '2-digit', minute: '2-digit', second: '2-digit' };
    return new Date(timestamp).toLocaleDateString('en-US', options);
}

const displayPagination = (page) => {
    const currentPage = parseInt(page);
    const totalPages = 3; // Aquí debes cambiarlo por el total de páginas que tengas
    let paginationHTML = '';
    for (let i = 1; i <= totalPages; i++) {
        if (i === currentPage) {
            paginationHTML += `<button onclick="loadGetMsg(${i})" disabled>${i}</button>`;
        } else {
            paginationHTML += `<button onclick="loadGetMsg(${i})">${i}</button>`;
        }
    }
    document.getElementById("pagination").innerHTML = paginationHTML;
}

const addLog = () => {
    const message = document.getElementById("message").value;
    const xhttp = new XMLHttpRequest();
    xhttp.onload = function() {
        if (this.status === 200) {
            alert("Log agregado exitosamente");
        } else {
            alert("Error al agregar el log");
        }
    }
    xhttp.open("POST", "http://localhost:35000/logs");
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send("message=" + encodeURIComponent(message));
}

const handleKeyPress = (event) => {
    if (event.keyCode === 13) {
        event.preventDefault();
        loadGetMsg(0);
    }
};
