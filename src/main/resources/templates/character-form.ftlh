<#import "layout.ftlh" as l>
<@l.page title=formTitle active=editing?string("", "add")>

    <h1>${formTitle}</h1>
    <hr>

    <form action="${formAction}" method="post">
        <div>
            <label for="charName">Character Name:</label>
            <input type="text" id="charName" name="name" value="${(character.name)!''}" placeholder="e.g., Kamen Rider Kuuga">
            <#if (errors.name)??><span class="error">${errors.name}</span></#if>
        </div>

        <div>
            <label for="charUniverse">Universe / Franchise:</label>
            <input type="text" id="charUniverse" name="universe" value="${(character.universe)!''}" placeholder="e.g., Heisei">
            <#if (errors.universe)??><span class="error">${errors.universe}</span></#if>
        </div>

        <div>
            <label for="charRiderType">Rider Type:</label>
            <input type="text" id="charRiderType" name="riderType" value="${(character.riderType)!''}" placeholder="e.g., Main Rider, Secondary Rider">
            <#if (errors.riderType)??><span class="error">${errors.riderType}</span></#if>
        </div>

        <div>
            <label for="charImageUrl">Image URL:</label>
            <input type="text" id="charImageUrl" name="imageUrl" value="${(character.imageUrl)!''}" placeholder="https://example.com/pic.png or /images/pic.png">
            <#if (errors.imageUrl)??><span class="error">${errors.imageUrl}</span></#if>
        </div>

        <div>
            <label for="charDescription">Description:</label>
            <textarea id="charDescription" name="description" rows="6" placeholder="Briefly describe the character's background, traits, personality, etc.">${(character.description)!''}</textarea>
            <#if (errors.description)??><span class="error">${errors.description}</span></#if>
        </div>

        <button type="submit"><#if editing>Save Changes<#else>Create Character</#if></button>
    </form>

</@l.page>
