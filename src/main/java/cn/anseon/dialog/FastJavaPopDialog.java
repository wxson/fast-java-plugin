package cn.anseon.dialog;

import cn.anseon.constants.CommonConstants;
import cn.anseon.domain.FastDomain;
import cn.anseon.proxy.CodeGenerateProxy;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import java.awt.event.*;

/**
 * Fast-Java Dialog
 *
 * @author GR
 * @date 2021-1-5 17:28
 */
public class FastJavaPopDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonConfirm;
    private JButton buttonCancel;
    private JTextField fastClassName;
    private JTextField fastClassProperty;
    /**
     * 右键绝对路劲
     */
    private final String actionAbsoluteDir;
    /**
     * 右键main/java 下的路劲
     */
    private final String actionDir;

    public FastJavaPopDialog(String actionAbsoluteDir, String actionDir) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonConfirm);
        this.actionAbsoluteDir = actionAbsoluteDir;
        this.actionDir = actionDir;
        this.registerEvent();
    }

    /**
     * 注册事件
     */
    private void registerEvent() {
        // 点击确认
        buttonConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClickConfirmEvent();
            }
        });

        // 点击取消
        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClickCancelEvent();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                onClickCancelEvent();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClickCancelEvent();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    /**
     * 点击确认事件
     */
    private void onClickConfirmEvent() {
        // add your code here
        dispose();
        // 获取输入的类名
        String fastClassNameText = fastClassName.getText();
        if (StringUtils.isBlank(fastClassNameText)) {
            return;
        }

        // 校验类名以DTO结尾
        if (fastClassNameText.length() > 3) {
            String afterThreeSuffix = fastClassNameText.substring(fastClassNameText.length() - 3);
            if (CommonConstants.DOMAIN_DTO_SUFFIX.equals(afterThreeSuffix.toUpperCase())) {
                fastClassNameText = fastClassNameText.substring(0, fastClassNameText.length() - 3);
            }
        }
        // 校验类名以DO、VO结尾
        else if (fastClassNameText.length() > 2) {
            String afterTwoSuffix = fastClassNameText.substring(fastClassNameText.length() - 2);
            if (CommonConstants.DOMAIN_DO_SUFFIX.equals(afterTwoSuffix.toUpperCase()) || CommonConstants.DOMAIN_VO_SUFFIX.equals(afterTwoSuffix.toUpperCase())) {
                fastClassNameText = fastClassNameText.substring(0, fastClassNameText.length() - 2);
            }
        }

        // 属性json
        String fastClassPropertyText = fastClassProperty.getText();
        // 构建fast java参数
        FastDomain fastDomain = new FastDomain()
                .setActionDir(actionDir)
                .setActionAbsoluteDir(actionAbsoluteDir)
                .setFastJavaClassName(fastClassNameText)
                .setFastJavaClassPropertyJson(fastClassPropertyText);
        // 执行代码生成
        CodeGenerateProxy.getInstance().run(fastDomain);
    }

    /**
     * 点击取消事件
     */
    private void onClickCancelEvent() {
        // add your code here if necessary
        dispose();
        fastClassName.setText("");
        fastClassProperty.setText("");
    }
}
