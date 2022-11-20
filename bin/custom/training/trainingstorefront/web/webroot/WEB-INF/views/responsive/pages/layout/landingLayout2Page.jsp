<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>

<template:page pageTitle="${pageTitle}">
    <cms:pageSlot position="Section1" var="feature">
        <cms:component component="${feature}" />
    </cms:pageSlot>
    <div class="row no-margin">
        <div class="col-xs-12 col-md-6 no-space">
            <cms:pageSlot position="Section2A" var="feature" element="div" class="row no-margin">
                <cms:component component="${feature}" element="div" class="col-xs-12 col-sm-6 no-space yComponentWrapper"/>
            </cms:pageSlot>
        </div>
        <div class="col-xs-12 col-md-6 no-space">
            <cms:pageSlot position="Section2B" var="feature" element="div" class="row no-margin">
                <cms:component component="${feature}" element="div" class="col-xs-12 col-sm-6 no-space yComponentWrapper"/>
            </cms:pageSlot>
        </div>
        <div class="col-xs-12">
            <cms:pageSlot position="Section2C" var="feature" element="div" class="landingLayout2PageSection2C">
                <cms:component component="${feature}" element="div" class="yComponentWrapper"/>
            </cms:pageSlot>
        </div>
    </div>

    <cms:pageSlot position="Section3" var="feature" element="div" class="row no-margin" >
        <cms:component component="${feature}" element="div" class="no-space yComponentWrapper"/>
    </cms:pageSlot>

    <cms:pageSlot position="Section4" var="feature" element="div" class="row no-margin">
        <cms:component component="${feature}" element="div" class="col-xs-6 col-md-3 no-space yComponentWrapper"/>
    </cms:pageSlot>

    <cms:pageSlot position="Section5" var="feature" element="div">
        <cms:component component="${feature}" element="div" class="yComponentWrapper"/>
    </cms:pageSlot>

    <h1 style="text-align : center">Student List</h1>
        <div class="container product-list-container">
            <c:set var="count" value="1"/>
            <c:forEach var="student" items="${studentList}">
                <div class="base-product-container">
                    <h3>
                        <span>${count}.</span>
                        ${student.fullName} (${student.id})
                    </h3>
                </div>
                <c:set var="count" value="${count+1}"/>
                <div class="variant-product-container">
                    <table>
                        <thead>
                                <th></th>
                                <th>Book ID</th>
                                <th>Book Title</th>
                                <th>Genre </th>
                        </thead>
                        <tbody>
                            <c:forEach var="book" items="${student.book}">
                                <tr>
                                    <td><img src="${book.image}" style="width: 200px"></td>
                                    <td>${book.id}</td>
                                    <td>${book.title}</td>
                                    <td>

                                            ${book.genre}<br>

                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:forEach>
        </div>

</template:page>
