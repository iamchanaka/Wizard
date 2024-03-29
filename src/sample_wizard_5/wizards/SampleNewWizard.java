package sample_wizard_5.wizards;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.core.runtime.*;
import org.eclipse.jface.operation.*;
import java.lang.reflect.InvocationTargetException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.core.resources.*;
import org.eclipse.core.runtime.CoreException;
import java.io.*;

import org.eclipse.ui.*;
import org.eclipse.ui.ide.IDE;

/**
 * This is a sample new wizard. Its role is to create a new file 
 * resource in the provided container. If the container resource
 * (a folder or a project) is selected in the workspace 
 * when the wizard is opened, it will accept it as the target
 * container. The wizard creates one file with the extension
 * "txt". If a sample multi-page editor (also available
 * as a template) is registered for the same extension, it will
 * be able to open it.
 */

public class SampleNewWizard extends Wizard implements INewWizard {
	
	private SampleNewWizardFirstPage firstPage;
	private SampleNewWizardSecondPage secondPage;
	
	private ISelection selection;

	/**
	 * Constructor for SampleNewWizard.
	 */
	public SampleNewWizard() {
		super();
		setNeedsProgressMonitor(true);
	}
	
	/**
	 * Adding the page to the wizard.
	 */	

	public void addPages() {
		firstPage = new SampleNewWizardFirstPage(selection);
		addPage(firstPage);
		secondPage = new SampleNewWizardSecondPage(selection);
		addPage(secondPage);
	}

	/**
	 * This method is called when 'Finish' button is pressed in
	 * the wizard. We will create an operation and run it
	 * using wizard as execution context.
	 */
	public boolean performFinish() {
		final String containerName = firstPage.getContainerName();
		final String fileName = firstPage.getFileName();
		
		//Get name and address from the first page
		
		final String name = firstPage.getName();
		final String address = firstPage.getAddress();
		
		//Get the Email address and the Mobile number from the second page
		
		final String email = secondPage.getEmail();
		final String mNumber = secondPage.getmNumber();
		
		
		
		
		IRunnableWithProgress op = new IRunnableWithProgress() {
			public void run(IProgressMonitor monitor) throws InvocationTargetException {
				try {
					doFinish(containerName, fileName,name,address,email,mNumber, monitor);
				} catch (CoreException e) {
					throw new InvocationTargetException(e);
				} finally {
					monitor.done();
				}
			}
		};
		try {
			getContainer().run(true, false, op);
//			getAddress().run(true, false,op);
		} catch (InterruptedException e) {
			return false;
		} catch (InvocationTargetException e) {
			Throwable realException = e.getTargetException();
			MessageDialog.openError(getShell(), "Error", realException.getMessage());
			return false;
		}
		
		System.out.println("Your name is "+firstPage.getName());
		System.out.println("Your address is "+firstPage.getAddress());
		
		System.out.println("Your email address is "+secondPage.getEmail());
		System.out.println("Your mobile number is "+secondPage.getmNumber());
				
		return true;
	}
	
	
	
	/**
	 * The worker method. It will find the container, create the
	 * file if missing or just replace its contents, and open
	 * the editor on the newly created file.
	 */

	private void doFinish(
		String containerName,
		String fileName,
		// declare the name and address
		
		String name,
		String address,
		String email,
		String mNumber,
		
		
		IProgressMonitor monitor)
		throws CoreException {
		monitor.beginTask("Creating " + fileName, 2);
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IResource resource = root.findMember(new Path(containerName));
		if (!resource.exists() || !(resource instanceof IContainer)) {
			throwCoreException("Container \"" + containerName + "\" does not exist.");
		}
		
		IContainer container = (IContainer) resource;
		final IFile file = container.getFile(new Path(fileName));
		
//		Print input in a text file		
		
		
		try {
			InputStream stream = openContentStream(name,address,email,mNumber);
			if (file.exists()) {
				file.setContents(stream, true, true, monitor);
			} 
			else {
				file.create(stream, true, monitor);
			}
			stream.close();
		} catch (IOException e) {
		}
		

		
		monitor.worked(1);
		monitor.setTaskName("Opening file for editing...");
		getShell().getDisplay().asyncExec(new Runnable() {
			public void run() {
				IWorkbenchPage page =
					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
				try {
					IDE.openEditor(page, file, true);
				} catch (PartInitException e) {
				}
			}
		});
		monitor.worked(1);
	}
	
	/**
	 * We will initialize file contents with a sample text.
	 */

//	Getting input from the first and second pages
	
	
	private InputStream openContentStream(String name,String address,String email,String mNumber) {
		String uDetails = ("Your user name is "+name+
						   "\nYour adddress is "+address+
						   "\nYour email address is "+email+
						   "\nYour moblie number is "+mNumber);
		return new ByteArrayInputStream(uDetails.getBytes());
	}
	
	
	
	private void throwCoreException(String message) throws CoreException {
		IStatus status =
			new Status(IStatus.ERROR, "Sample_Wizard_5", IStatus.OK, message, null);
		throw new CoreException(status);
	}

	/**
	 * We will accept the selection in the workbench to see if
	 * we can initialize from it.
	 * @see IWorkbenchWizard#init(IWorkbench, IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.selection = selection;
	}
}