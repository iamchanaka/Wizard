package sample_wizard_5.wizards;

/*import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;*/
import org.eclipse.jface.viewers.ISelection;
//import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
/*import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;*/
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
//import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
//import org.eclipse.ui.dialogs.ContainerSelectionDialog;

public class SampleNewWizardSecondPage extends WizardPage {
//	private Text containerText;
//	private Text fileText;
	
	//Newly added for the page
	private Text email;
	private Text mNumber;

//	private ISelection selection;

	/**
	 * Constructor for SampleNewWizardPage.
	 * 
	 * @param pageName
	 */
	public SampleNewWizardSecondPage(ISelection selection) {
		super("wizardPage");
		setTitle("Second Page");
		setDescription("This wizard creates a new file with *.txt extension that can be opened by a multi-page editor.");
//		this.selection = selection;
	}

	/**
	 * @see IDialogPage#createControl(Composite)
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		
		/*container.setLayout(layout);
		layout.numColumns = 3;
		layout.verticalSpacing = 9;*/
//		Label label = new Label(container, SWT.NULL);
		//label.setText("&Container:");

//		containerText = new Text(container, SWT.BORDER | SWT.SINGLE);
	//	GridData gd = new GridData(GridData.FILL_HORIZONTAL);
//		containerText.setLayoutData(gd);
//		containerText.addModifyListener(new ModifyListener() {
//			public void modifyText(ModifyEvent e) {
//				dialogChanged();
//			}
//		});
//
//		Button button = new Button(container, SWT.PUSH);
//		button.setText("Browse...");
//		button.addSelectionListener(new SelectionAdapter() {
//			public void widgetSelected(SelectionEvent e) {
//				handleBrowse();
//			}
//		});
//		label = new Label(container, SWT.NULL);
//		label.setText("&File name:");

 //		fileText = new Text(container, SWT.BORDER | SWT.SINGLE);
 //		gd = new GridData(GridData.FILL_HORIZONTAL);
 //		fileText.setLayoutData(gd);
 /* 		fileText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				dialogChanged();
			}
		});
*/		
		
		// Set another text spaces for get the user inputs
		
		GridLayout layout2 = new GridLayout();
		container.setLayout(layout2);
		layout.numColumns = 3;
		layout.verticalSpacing = 9;
		Label label2 = new Label(container, SWT.NULL);
		label2.setText("&Email:");

		email = new Text(container, SWT.BORDER | SWT.SINGLE);
		GridData gd2 = new GridData(GridData.FILL_HORIZONTAL);
		email.setLayoutData(gd2);
		email.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				dialogChanged();
			}
		});
		
		label2 = new Label(container, SWT.NULL);
		label2.setText("&Mobile Number:");

		mNumber = new Text(container, SWT.BORDER | SWT.SINGLE);
		gd2 = new GridData(GridData.FILL_HORIZONTAL);
		mNumber.setLayoutData(gd2);
		mNumber.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				dialogChanged();
			}
		});
				
		initialize();
		dialogChanged();
		setControl(container);
	}

	/**
	 * Tests if the current workbench selection is a suitable container to use.
	 */

	private void initialize() {
//		if (selection != null && selection.isEmpty() == false
//				&& selection instanceof IStructuredSelection) {
//			IStructuredSelection ssel = (IStructuredSelection) selection;
//			if (ssel.size() > 1)
//				return;
//			Object obj = ssel.getFirstElement();
//			if (obj instanceof IResource) {
//				IContainer container;
//				if (obj instanceof IContainer)
//					container = (IContainer) obj;
//				else
//					container = ((IResource) obj).getParent();
//				containerText.setText(container.getFullPath().toString());
//			}
//		}
//		fileText.setText("test2.txt");
		email.setText("");
		mNumber.setText("");
	}

//	/**
//	 * Uses the standard container selection dialog to choose the new value for
//	 * the container field.
//	 */
//
//	private void handleBrowse() {
//		ContainerSelectionDialog dialog = new ContainerSelectionDialog(
//				getShell(), ResourcesPlugin.getWorkspace().getRoot(), false,
//				"Select new file container");
//		if (dialog.open() == ContainerSelectionDialog.OK) {
//			Object[] result = dialog.getResult();
//			if (result.length == 1) {
//				containerText.setText(((Path) result[0]).toString());
//			}
//		}
//	}

	/**
	 * Ensures that both text fields are set.
	 */

	private void dialogChanged() {
//		IResource container = ResourcesPlugin.getWorkspace().getRoot()
//				.findMember(new Path(getContainerName()));
 //		String fileName = getFileName();
		
		// Validate the new user inputs
		
		String email = getEmail();
		String mNumber = getmNumber();
		
//		if (getContainerName().length() == 0) {
//			updateStatus("File container must be specified");
//			return;
//		}
//		if (container == null
//				|| (container.getType() & (IResource.PROJECT | IResource.FOLDER)) == 0) {
//			updateStatus("File container must exist");
//			return;
//		}
//		if (!container.isAccessible()) {
//			updateStatus("Project must be writable");
//			return;
//		}
 /*		if (fileName.length() == 0) {
			updateStatus("File name must be specified");
			return;
		}
		if (fileName.replace('\\', '/').indexOf('/', 1) > 0) {
			updateStatus("File name must be valid");
			return;
		}
		int dotLoc = fileName.lastIndexOf('.');
		if (dotLoc != -1) {
			String ext = fileName.substring(dotLoc + 1);
			if (ext.equalsIgnoreCase("txt") == false) {
				updateStatus("File extension must be \"txt\"");
				return;
			}
		}
 */		
		if (email.length()==0){
			updateStatus("Email must be specified");
			return;
		}
		if (mNumber.length()==0){
			updateStatus("Mobile Number must be specified");
			return;
		}
		updateStatus(null);
	}

	private void updateStatus(String message) {
		setErrorMessage(message);
		setPageComplete(message == null);
	}

//	public String getContainerName() {
//		return containerText.getText();
//	}
//	
	// Create the getters and setters

 /*	public String getFileName() {
		return fileText.getText();
	}
 */
	public String getEmail() {
		return email.getText();
	}
	/*public void setName(){
		this.name=name;
	}*/

	public String getmNumber() {
		return mNumber.getText();
	}
	/*public void setAddress(){
		this.address=address;
	}*/
}