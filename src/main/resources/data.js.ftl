function data_${valueType} () {
        return "" +
            "Date,${valueType},${locationName}\n" +
            <#list values as value>
                "${value.dayFormattedForReport},${value.min},${value.average},${value.max}\n" +
            </#list>
            + "";
}