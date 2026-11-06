<#import "layout.ftlh" as l>
<@l.page title="Not Found" active="">

    <h1>Character not found</h1>
    <hr>
    <p>${errorMessage!"Sorry, something went wrong."}</p>
    <p><a href="/" class="details-btn">Back to the gallery</a></p>

</@l.page>
