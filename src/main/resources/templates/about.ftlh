<#import "layout.ftlh" as l>
<@l.page title="About" active="about">

  <h1>About Me</h1>
  <hr>
  <p>My name is Toni Graham and this is my project for CSC 340 Software Engineering. The purpose of this website is to showcase a collection of characters from the Kamen Rider franchise. My main interests include:</p>
  <ul>
    <li>Tokusatsu (including Kamen Rider, Super Sentai, Ultraman, etc.)</li>
    <li>Magical girl anime</li>
    <li>Video games</li>
    <li>Reading</li>
    <li>Mechas, gunpla</li>
  </ul>
  <p>The reason why I chose the Kamen Rider theme is because I'm a huge fan of Kamen Rider and wanted to show off my favorite characters. Kuuga especially is my favorite, him being one of my most liked seasons that I watched from the series. For this website I chose to keep the UI and CSS very simple to help the characters stand out more.</p>
  <figure>
      <img src="/images/KRWi-Wizardflame_webp.png" alt="Kamen Rider Wizard Flame Form" width="300px">
      <figcaption>A picture of Haruto Soma aka Kamen Rider Wizard, one of my favorite Kamen Riders.</figcaption>
  </figure>

  <h2>How this app is built</h2>
  <p>This is a Spring Boot web application with four layers. The <b>entity</b> (RiderCharacter) describes one character and maps to a table in a PostgreSQL database hosted on Neon. The <b>repository</b> uses Spring Data JPA to read and write that table. The <b>service</b> holds the logic, such as searching and updating characters. The <b>controllers</b> receive requests and talk only to the service.</p>
  <p>There are two controllers. A REST controller serves JSON at <code>/api/characters</code>. A separate page controller builds the HTML pages you are looking at, using FreeMarker templates that share one layout. Both controllers use the same service and database, so a character added through the form shows up in the API and the other way around. The app runs in a Docker container deployed on Render.</p>

</@l.page>
