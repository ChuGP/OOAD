package adapter;

import ClassDetailInfo.*;

import java.util.ArrayList;
import java.util.List;

public class ClassDetailInfoDTO {
    private List<String> methods;
    private List<String> variables;
    private String className;
    public ClassDetailInfoDTO(ClassDetailInfo classDetailInfo){
        setClassDetailInfo(classDetailInfo);
    }

    public List<String> getMethods() {
        return methods;
    }

    public List<String> getVariables() {
        return variables;
    }

    public String getClassName() {
        return className;
    }

    public void setClassDetailInfo(ClassDetailInfo classDetailInfo) {
        parseClassName(classDetailInfo);
        parseMethod(classDetailInfo);
        parseVariable(classDetailInfo);
    }

    private void parseMethod(ClassDetailInfo classDetailInfo){
        List<ClassMemberAbstract>memberMethods=classDetailInfo.getMemberFunction();
        List<String>methods=new ArrayList<>();
        for(ClassMemberAbstract c:memberMethods)
            methods.add(parseFormat(c));
        this.methods=methods;
    }

    private void parseVariable(ClassDetailInfo classDetailInfo){
        List<ClassMemberAbstract>memberVariable=classDetailInfo.getMemberVariable();
        List<String>variables=new ArrayList<>();
        for(ClassMemberAbstract c:memberVariable)
            variables.add(parseFormat(c));
        this.variables=variables;
    }

    private void parseClassName(ClassDetailInfo classDetailInfo){
        this.className=classDetailInfo.getClassName();
    }

    public String parseFormat(ClassMemberAbstract classMemberAbstract){
        StringBuilder builder=new StringBuilder();
        switch (classMemberAbstract.getReference()){
            case "Public":
                builder.append(" + ");
                break;
            case "Private":
                builder.append(" - ");
                break;
            case "Protected":
                builder.append(" # ");
                break;
            case "Package":
                builder.append(" ~ ");
            default:
                break;
        }
        builder.append(classMemberAbstract.getName()).append(" : ").append(classMemberAbstract.getType());
        return builder.toString();
    }

}


