<#import "layout.ftlh" as l>
<@l.page title=character.name active="">

    <h1>${character.name}</h1>
    <hr>

    <#assign img = "/images/placeholder.svg">
    <#if character.imageUrl?has_content>
        <#assign img = character.imageUrl>
    </#if>
    <figure>
        <img src="${img}" alt="${character.name}" width="300px">
        <figcaption>${character.name}</figcaption>
    </figure>

    <p><b>Universe:</b> ${character.universe!"Unknown"}</p>
    <p><b>Rider type:</b> ${character.riderType!"Unknown"}</p>
    <p style="white-space: pre-line;">${character.description!""}</p>

    <div class="button-row">
        <a href="/characters/${character.characterId?c}/edit" class="details-btn">Edit</a>
        <form action="/characters/${character.characterId?c}/delete" method="post"
              onsubmit="return confirm('Are you sure you want to delete this character?');">
            <button type="submit" class="danger-btn">Delete</button>
        </form>
    </div>

    <p><a href="/">&larr; Back to the gallery</a></p>

</@l.page>
