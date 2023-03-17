package org.micro.common;

public class JobsQueryUtils {

    public static String getJobCountQuery() {
        StringBuilder sql = new StringBuilder();
        sql.append("select\n" +
                "\tcount (distinct j.id) as itemCount\n" +
                "from\n" +
                "\tjobs j\n" +
                "left outer join job_location jl on \n" +
                "\tjl.job_id = j.id\n" +
                "left outer join company_person_details cpd on\n" +
                "\tcpd.job_id = j.id");
        return sql.toString();
    }

    public static String getJobCountByUserIdQuery(Long userId) {
        StringBuilder sql = new StringBuilder();
        sql.append("select\n" +
                "\tcount (distinct j.id) as itemCount\n" +
                "from\n" +
                "\tjobs j\n" +
                "left outer join job_location jl on \n" +
                "\tjl.job_id = j.id\n" +
                "left outer join company_person_details cpd on\n" +
                "\tcpd.job_id = j.id" +
                " where j.user_id = "+ userId.toString() );
        return sql.toString();
    }
}
