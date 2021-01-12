package cn.anseon.actions;

import cn.anseon.dialog.FastJavaPopDialog;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Fast-Java Action
 *
 * @author GR
 */
public class FastJavaPopDialogAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
        // TODO: insert action logic here
        Project project = anActionEvent.getProject();
        if (Objects.isNull(project)) {
            return;
        }

        // 获取虚拟文件
        VirtualFile virtualFile = anActionEvent.getData(CommonDataKeys.VIRTUAL_FILE);
        if (Objects.isNull(virtualFile)) {
            return;
        }

        // 非目录则获取上级目录
        if (!virtualFile.isDirectory()) {
            virtualFile = virtualFile.getParent();
        }

        // 截取src/main/java后的action路劲
        String actionDir = StringUtils.substringAfter(virtualFile.getPath(), "src/main/java/");
        if (StringUtils.isBlank(actionDir)) {
            return;
        }

        FastJavaPopDialog dialog = new FastJavaPopDialog(project, virtualFile.getPath(), actionDir);
        dialog.show();
    }
}
