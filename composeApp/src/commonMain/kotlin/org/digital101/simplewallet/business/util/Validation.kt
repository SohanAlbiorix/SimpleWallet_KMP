package org.digital101.simplewallet.business.util

fun isValidEmail(email: String): Boolean {
    val regex = Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}\$")
    return email.isEmpty() || !email.matches(regex)
}

fun isValidPassword(password: String): Boolean {
    val regex = Regex("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,15}$")
    return password.isEmpty() || !password.matches(regex)
}

fun isPreferredName(name: String): Boolean {
    return name.isEmpty()
}

fun isReligion(religion: String): Boolean {
    return religion.isEmpty()
}

fun isMaritalStatus(status: String): Boolean {
    return status.isEmpty()
}

fun isAddress1(addressOne: String): Boolean {
    return addressOne.isEmpty()
}

fun isAddress2(addressTwo: String): Boolean {
    return addressTwo.isEmpty()
}

fun isPostCode(postCode: String): Boolean {
    return postCode.isEmpty()
}

fun isCity(city: String): Boolean {
    return city.isEmpty()
}

fun isState(state: String): Boolean {
    return state.isEmpty()
}

fun isEmployeeType(type: String): Boolean {
    return type.isEmpty()
}

fun isEmployeeIndustry(industry: String): Boolean {
    return industry.isEmpty()
}

fun isNameOfEmployee(employeeName: String): Boolean {
    return employeeName.isEmpty()
}

fun isNameOccupation(occupation: String): Boolean {
    return occupation.isEmpty()
}

fun isAnnualIncome(income: String): Boolean {
    return income.isEmpty()
}


