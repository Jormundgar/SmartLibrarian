document
    .getElementById("loginForm")
    .addEventListener("submit", async function (event) {
        event.preventDefault();
        let formData = {
            username: $("#username").val(),
            password: $("#password").val()
        };
        let response = await $.ajax({
            url: 'http://localhost:8060/auth/login',
            method: 'POST',
            contentType: "application/json",
            data: JSON.stringify(formData),
            success: window.location.href = "http://localhost:8060/books"
        });

        // let json = await response.json();
        // if (json.checking) {
        //     window.location.href = "http://localhost:8080/admin";
        // } else {
        //     window.location.href = "http://localhost:8080/user";
        // }
    });