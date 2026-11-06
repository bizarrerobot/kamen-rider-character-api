<#import "layout.ftlh" as l>
<@l.page title="Toni's Website" active="home">

    <h1 style="text-align: center;">Kamen Rider Character Gallery</h1>
    <hr>

    <form class="search-container" action="/" method="get">
        <input type="text" name="name" value="${name!""}" placeholder="Search by name..." />
        <input type="text" name="universe" value="${universe!""}" placeholder="Universe (e.g. Heisei)" />
        <button type="submit">Search</button>
        <a href="/">Clear</a>
    </form>

    <#if characters?has_content>
        <div class="image-row">
            <#list characters as c>
                <#assign img = "/images/placeholder.svg">
                <#if c.imageUrl?has_content>
                    <#assign img = c.imageUrl>
                </#if>
                <figure class="gallery-item">
                    <a href="/characters/${c.characterId?c}">
                        <img src="${img}" alt="${c.name}">
                    </a>
                    <figcaption>
                        <b>${c.name}</b><br>
                        Universe: ${c.universe!"Unknown"}<br>
                        Rider type: ${c.riderType!"Unknown"}
                    </figcaption>
                    <a href="/characters/${c.characterId?c}" class="details-btn">View Details</a>
                </figure>
            </#list>
        </div>
    <#else>
        <p style="text-align: center;">No characters found.</p>
    </#if>

</@l.page>
